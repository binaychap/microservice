package org.binay.moviecatelogservice.model;

import java.util.List;

public record UserRating(String userId, List<Rating> ratings) {
}
