package com.javacourse.stormnetbot.shared.tool;

import com.javacourse.stormnetbot.shared.entity.User;
import com.javacourse.stormnetbot.shared.entity.security.Feature;
import com.javacourse.stormnetbot.shared.entity.security.Role;

import java.util.List;

public class SecurityUtil {

    public static boolean hasFeature(User user, Feature feature) {
        Role role = user.getRole();
        if (role.equals(Role.SUPER_ADMIN)) {
            return true;
        } else if (role.equals(Role.BLOCKED)) {
            return false;
        } else {
            List<Feature> features = role.getFeatures();
            return features.contains(feature);
        }
    }
}
