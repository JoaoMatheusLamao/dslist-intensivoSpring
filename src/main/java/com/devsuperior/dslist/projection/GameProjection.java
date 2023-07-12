package com.devsuperior.dslist.projection;

public interface GameProjection {
    Long getId();
    String getTitle();
    Integer getYear();
    String getGenre();
    String getPlataforms();
    Double getScore();
    String getImgUrl();
    String getShortDescription();
    String getLongDescription();
    Integer getPosition();

}
