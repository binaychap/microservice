package org.binay.ratingsdataservice.model;

import java.util.List;

public record UserRating(String userId, List<Rating> ratings) {
}
