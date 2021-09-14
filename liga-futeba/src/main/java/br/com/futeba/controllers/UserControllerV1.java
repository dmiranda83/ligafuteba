package br.com.futeba.controllers;

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

import br.com.futeba.dtos.TeamDto;
import br.com.futeba.dtos.UserDto;
import br.com.futeba.dtos.UserLoginDto;
import br.com.futeba.models.Category;
import br.com.futeba.models.Place;
import br.com.futeba.models.Team;
import br.com.futeba.models.User;
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

    @PostMapping("/user/sigin")
    public ResponseEntity<User> sigin(@RequestBody final UserDto dto) {
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
                    .role(dto.getRole())
                    .password(passwordService.getSecureHash(dto.getPassword()))
                    .cellPhone(dto.getCellPhone())
                    .changePassword(false)
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
        Optional<Place> placeFound = placeService.findByZipCode(dto.getTeam().getPlace().getZipCode());
        Optional<Category> categoryFound = categoryService.findByName(dto.getTeam().getCategory().getName());

        category = categoryFound.isPresent()
                ? categoryFound.get()
                : buildCategory(dto.getTeam());

        place = placeFound.isPresent()
                ? placeFound.get()
                : buildPlace(dto.getTeam());

        Team team = buildTeam(dto.getTeam(), place, category);
        teams.add(team);

        user.setId(id);
        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        user.setCellPhone(dto.getCellPhone() != null ? dto.getCellPhone() : user.getCellPhone());
        user.setTeams(teams);

        service.update(user);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<User> getHttpStatusBadRequest(final String errorKey,
            final String defaultMessage) {
        return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(User.class.getName(),
                        errorKey, defaultMessage))
                .body(null);
    }

    private Team buildTeam(final TeamDto dto, final Place place, final Category category) {
        return Team.builder()
                .name(dto.getName())
                .away(dto.getAway())
                .responsibleName(dto.getResponsibleName())
                .phoneContact1(dto.getPhoneContact1())
                .phoneContact2(dto.getPhoneContact2())
                .category(category)
                .place(place)
                .build();
    }

    private Place buildPlace(final TeamDto dto) {
        return Place.builder()
                .name(dto.getPlace().getName())
                .type(dto.getPlace().getType())
                .address(dto.getPlace().getAddress())
                .city(dto.getPlace().getCity())
                .neighborhood(dto.getPlace().getNeighborhood())
                .zipCode(dto.getPlace().getZipCode())
                .build();
    }

    private Category buildCategory(final TeamDto dto) {
        return Category.builder()
                .name(dto.getCategory().getName())
                .build();
    }
}
