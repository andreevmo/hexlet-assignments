package exercise.controllers;

import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;
import org.apache.commons.validator.routines.EmailValidator;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(firstName -> !firstName.isEmpty(), "Поле 'Имя' не может быть пустым.");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(lastName -> !lastName.isEmpty(), "Поле 'Фамилия' не может быть пустым.");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(password -> StringUtils.isNumeric(password),
                        "Поле 'Пароль' должно содержать только цифры")
                .check(password -> password.length() >= 4, "Поле 'Пароль' не должно быть менее 4 символов.");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(email -> EmailValidator.getInstance().isValid(email),
                        "Поле 'Электронная почта' не соответствует формату");
        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                firstNameValidator, lastNameValidator, passwordValidator, emailValidator);

        User user = new User(ctx.formParam("firstName"), ctx.formParam("lastName"),
                ctx.formParam("email"), ctx.formParam("password"));

        if (!(errors.isEmpty())) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);
            ctx.render("users/new.html");
            return;
        }

        user.save();
        ctx.sessionAttribute("flash", "Пользователь успешно создан.");
        ctx.redirect("/users");
        // END
    };
}
