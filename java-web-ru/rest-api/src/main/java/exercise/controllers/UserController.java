package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.http.HttpCode;
import org.apache.commons.validator.routines.EmailValidator;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        ctx.json(users);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.eq(Integer.parseInt(id))
                .findOne();
        if (user != null) {
            ctx.json(user);
            return;
        }

        ctx.status(HttpCode.NOT_FOUND);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(u -> !u.getFirstName().isEmpty(), "Firstname can't be empty.")
                .check(u -> EmailValidator.getInstance().isValid(u.getEmail()), "Incorrect field Email")
                .get();

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.eq(Integer.parseInt(id))
                .delete();
        // END
    };
}
