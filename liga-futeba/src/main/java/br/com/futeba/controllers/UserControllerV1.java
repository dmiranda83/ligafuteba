package br.com.futeba.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.futeba.domain.Role;
import br.com.futeba.dtos.CategoryDto;
import br.com.futeba.dtos.PlaceDto;
import br.com.futeba.dtos.TeamDto;
import br.com.futeba.dtos.UserDto;
import br.com.futeba.dtos.UserLoginDto;
import br.com.futeba.models.Category;
import br.com.futeba.models.Place;
import br.com.futeba.models.Team;
import br.com.futeba.models.User;
import br.com.futeba.models.Week;
import br.com.futeba.services.CategoryService;
import br.com.futeba.services.PasswordService;
import br.com.futeba.services.PlaceService;
import br.com.futeba.services.UserService;
import br.com.futeba.utils.HeaderUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerV1.class);

    @Autowired
    private UserService service;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> listAll() {
        logger.info("Listing all Users");
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> signup(@RequestBody final UserDto dto) {
        try {
            Optional<User> foundUser = service.findByEmail(dto.getEmail());
            if (foundUser.isPresent()) {
                return getHttpStatusBadRequest("userExists",
                        "A new user cannot exist");
            }

            User userBuild = User.builder()
                    .active(true)
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .role(dto.getRole() != null ? dto.getRole() : Role.ADMINISTRATOR_TEAM)
                    .password(passwordService.getSecureHash(dto.getPassword()))
                    .cellPhone(dto.getCellPhone())
                    .changePassword(false)
                    .teams(new HashSet<>())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userBuild));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil
                            .createErrorAlert("Check if parameters are correct!"))
                    .body(null);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody @Valid final UserLoginDto dto) {
        Optional<User> login = service.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(login.orElseGet(null));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") final long id,
            @RequestBody final UserDto dto) {
        Optional<User> foundUser = service.findById(id);
        Set<Team> teams = new HashSet<>();
        User user = new User();
        if (foundUser.isPresent()) {
            user = foundUser.get();
            teams = user.getTeams();
        }

        Place place;
        Category category;
        TeamDto team = dto.getTeam();
		Optional<Place> placeFound = placeService.findByZipCode(team.getPlace().getZipCode());
        Optional<Category> categoryFound = categoryService.findByName(team.getCategory().getName());

        category = categoryFound.isPresent() ? categoryFound.get() : buildCategory(team.getCategory());

        place = placeFound.isPresent() ? placeFound.get() : buildPlace(team.getPlace());
        place.setType(""); // Retirar qdo implementar a criacao do tipo de local(Quadra/Campo)
        
        
        Team updatedTeam = buildTeam(team, place, category);
        teams.add(updatedTeam);
        
        User.builder()
        .id(id)
        .name(dto.getName() != null ? dto.getName() : user.getName())
        .cellPhone(dto.getCellPhone() != null ? dto.getCellPhone() : user.getCellPhone())
        .teams(teams).build();


        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(user));
    }

    private ResponseEntity<User> getHttpStatusBadRequest(final String errorKey,
            final String defaultMessage) {
        return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(User.class.getName(),
                        errorKey, defaultMessage))
                .body(null);
    }

    private Team buildTeam(final TeamDto dto, final Place place, final Category category) {
    	 Set<Week> weeks = new HashSet<>();
         if (dto.getWeeks() != null) {
        	 dto.getWeeks().forEach(w -> weeks.add(Week.builder().name(w.getName()).build()));
         }
        return Team.builder()
                .name(dto.getName())
                .away(dto.getAway())
                .responsibleName(dto.getResponsibleName())
                .phoneContact1(dto.getPhoneContact1())
                .phoneContact2(dto.getPhoneContact2())
                .category(category)
                .place(place)
                .players(new HashSet<>())
                .awayGames(new ArrayList<>())
                .homeGames(new ArrayList<>())
                .weeks(weeks)
                .build();
    }

    private Place buildPlace(final PlaceDto placeDto) {
        return Place.builder()
                .name(placeDto.getName())
                .type(placeDto.getType())
                .address(placeDto.getAddress())
                .city(placeDto.getCity())
                .neighborhood(placeDto.getNeighborhood())
                .zipCode(placeDto.getZipCode())
                .build();
    }

    private Category buildCategory(final CategoryDto categoryDto) {
		return Category.builder()
                .name(categoryDto.getName())
                .build();
    }
}
