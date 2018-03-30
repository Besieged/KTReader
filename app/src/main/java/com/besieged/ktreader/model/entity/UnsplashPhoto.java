package com.besieged.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/6.
 */

public class UnsplashPhoto {

    /**
     * id : GQIGk5L1Ppk
     * created_at : 2017-06-05T14:34:14-04:00
     * updated_at : 2017-06-06T07:48:49-04:00
     * width : 7360
     * height : 4912
     * color : #E6C8CC
     * slug : null
     * downloads : 177
     * likes : 11
     * views : 246
     * liked_by_user : false
     * exif : {"make":"Sony","model":"ILCE-7R","exposure_time":"1/50","aperture":"6.3","focal_length":"313","iso":320}
     * location : {"title":"Iceland","name":"Iceland","city":null,"country":"Iceland","position":{"latitude":64.9779033004117,"longitude":-18.9387808618164}}
     * current_user_collections : []
     * urls : {"raw":"https://images.unsplash.com/photo-1496687421442-315106b0da41","full":"https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=fdd136461f8d60677f01e99f347b1fbc","regular":"https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=ee612007ec1fce61bb1bf86d81b0d81a","small":"https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=fa06c0c0946ecfa50db6334ea3ad4729","thumb":"https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=0fc197fdfb74711fbf048ec3720820bd"}
     * categories : []
     * links : {"self":"https://api.unsplash.com/photos/GQIGk5L1Ppk","html":"http://unsplash.com/photos/GQIGk5L1Ppk","download":"http://unsplash.com/photos/GQIGk5L1Ppk/download","download_location":"https://api.unsplash.com/photos/GQIGk5L1Ppk/download"}
     * user : {"id":"FHVSyyKd0o8","updated_at":"2017-06-06T07:48:49-04:00","username":"r3dmax","name":"Jonatan Pie","first_name":"Jonatan","last_name":"Pie","portfolio_url":null,"bio":"","location":"Back to Iceland","total_likes":74,"total_photos":79,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=16e3178e6327def37463f81737b4b5f7","medium":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=b882aec795cb771024ab0a0f1ae8f045","large":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=c0b0b0d08a17827c393a2c1d60c0fda9"},"links":{"self":"https://api.unsplash.com/users/r3dmax","html":"http://unsplash.com/@r3dmax","photos":"https://api.unsplash.com/users/r3dmax/photos","likes":"https://api.unsplash.com/users/r3dmax/likes","portfolio":"https://api.unsplash.com/users/r3dmax/portfolio","following":"https://api.unsplash.com/users/r3dmax/following","followers":"https://api.unsplash.com/users/r3dmax/followers"}}
     */

    @SerializedName("id")
    public String id;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("width")
    public int width;
    @SerializedName("height")
    public int height;
    @SerializedName("color")
    public String color;
    @SerializedName("slug")
    public Object slug;
    @SerializedName("downloads")
    public int downloads;
    @SerializedName("likes")
    public int likes;
    @SerializedName("views")
    public int views;
    @SerializedName("liked_by_user")
    public boolean likedByUser;
    @SerializedName("exif")
    public ExifEntity exif;
    @SerializedName("location")
    public LocationEntity location;
    @SerializedName("urls")
    public UrlsEntity urls;
    @SerializedName("links")
    public LinksEntity links;
    @SerializedName("user")
    public UserEntity user;
    @SerializedName("current_user_collections")
    public List<?> currentUserCollections;
    @SerializedName("categories")
    public List<?> categories;

    public static UnsplashPhoto objectFromData(String str) {

        return new Gson().fromJson(str, UnsplashPhoto.class);
    }

    public static class ExifEntity {
        /**
         * make : Sony
         * model : ILCE-7R
         * exposure_time : 1/50
         * aperture : 6.3
         * focal_length : 313
         * iso : 320
         */

        @SerializedName("make")
        public String make;
        @SerializedName("model")
        public String model;
        @SerializedName("exposure_time")
        public String exposureTime;
        @SerializedName("aperture")
        public String aperture;
        @SerializedName("focal_length")
        public String focalLength;
        @SerializedName("iso")
        public int iso;

        public static ExifEntity objectFromData(String str) {

            return new Gson().fromJson(str, ExifEntity.class);
        }
    }

    public static class LocationEntity {
        /**
         * title : Iceland
         * name : Iceland
         * city : null
         * country : Iceland
         * position : {"latitude":64.9779033004117,"longitude":-18.9387808618164}
         */

        @SerializedName("title")
        public String title;
        @SerializedName("name")
        public String name;
        @SerializedName("city")
        public Object city;
        @SerializedName("country")
        public String country;
        @SerializedName("position")
        public PositionEntity position;

        public static LocationEntity objectFromData(String str) {

            return new Gson().fromJson(str, LocationEntity.class);
        }

        public static class PositionEntity {
            /**
             * latitude : 64.9779033004117
             * longitude : -18.9387808618164
             */

            @SerializedName("latitude")
            public double latitude;
            @SerializedName("longitude")
            public double longitude;

            public static PositionEntity objectFromData(String str) {

                return new Gson().fromJson(str, PositionEntity.class);
            }
        }
    }

    public static class UrlsEntity {
        /**
         * raw : https://images.unsplash.com/photo-1496687421442-315106b0da41
         * full : https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=fdd136461f8d60677f01e99f347b1fbc
         * regular : https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=ee612007ec1fce61bb1bf86d81b0d81a
         * small : https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=fa06c0c0946ecfa50db6334ea3ad4729
         * thumb : https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=0fc197fdfb74711fbf048ec3720820bd
         */

        @SerializedName("raw")
        public String raw;
        @SerializedName("full")
        public String full;
        @SerializedName("regular")
        public String regular;
        @SerializedName("small")
        public String small;
        @SerializedName("thumb")
        public String thumb;

        public static UrlsEntity objectFromData(String str) {

            return new Gson().fromJson(str, UrlsEntity.class);
        }
    }

    public static class LinksEntity {
        /**
         * self : https://api.unsplash.com/photos/GQIGk5L1Ppk
         * html : http://unsplash.com/photos/GQIGk5L1Ppk
         * download : http://unsplash.com/photos/GQIGk5L1Ppk/download
         * download_location : https://api.unsplash.com/photos/GQIGk5L1Ppk/download
         */

        @SerializedName("self")
        public String self;
        @SerializedName("html")
        public String html;
        @SerializedName("download")
        public String download;
        @SerializedName("download_location")
        public String downloadLocation;

        public static LinksEntity objectFromData(String str) {

            return new Gson().fromJson(str, LinksEntity.class);
        }
    }

    public static class UserEntity {
        /**
         * id : FHVSyyKd0o8
         * updated_at : 2017-06-06T07:48:49-04:00
         * username : r3dmax
         * name : Jonatan Pie
         * first_name : Jonatan
         * last_name : Pie
         * portfolio_url : null
         * bio :
         * location : Back to Iceland
         * total_likes : 74
         * total_photos : 79
         * total_collections : 0
         * profile_image : {"small":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=16e3178e6327def37463f81737b4b5f7","medium":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=b882aec795cb771024ab0a0f1ae8f045","large":"https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=c0b0b0d08a17827c393a2c1d60c0fda9"}
         * links : {"self":"https://api.unsplash.com/users/r3dmax","html":"http://unsplash.com/@r3dmax","photos":"https://api.unsplash.com/users/r3dmax/photos","likes":"https://api.unsplash.com/users/r3dmax/likes","portfolio":"https://api.unsplash.com/users/r3dmax/portfolio","following":"https://api.unsplash.com/users/r3dmax/following","followers":"https://api.unsplash.com/users/r3dmax/followers"}
         */

        @SerializedName("id")
        public String id;
        @SerializedName("updated_at")
        public String updatedAt;
        @SerializedName("username")
        public String username;
        @SerializedName("name")
        public String name;
        @SerializedName("first_name")
        public String firstName;
        @SerializedName("last_name")
        public String lastName;
        @SerializedName("portfolio_url")
        public Object portfolioUrl;
        @SerializedName("bio")
        public String bio;
        @SerializedName("location")
        public String location;
        @SerializedName("total_likes")
        public int totalLikes;
        @SerializedName("total_photos")
        public int totalPhotos;
        @SerializedName("total_collections")
        public int totalCollections;
        @SerializedName("profile_image")
        public ProfileImageEntity profileImage;
        @SerializedName("links")
        public LinksEntityX links;

        public static UserEntity objectFromData(String str) {

            return new Gson().fromJson(str, UserEntity.class);
        }

        public static class ProfileImageEntity {
            /**
             * small : https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=16e3178e6327def37463f81737b4b5f7
             * medium : https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=b882aec795cb771024ab0a0f1ae8f045
             * large : https://images.unsplash.com/profile-1470091078573-15c47f5750e7?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=c0b0b0d08a17827c393a2c1d60c0fda9
             */

            @SerializedName("small")
            public String small;
            @SerializedName("medium")
            public String medium;
            @SerializedName("large")
            public String large;

            public static ProfileImageEntity objectFromData(String str) {

                return new Gson().fromJson(str, ProfileImageEntity.class);
            }
        }

        public static class LinksEntityX {
            /**
             * self : https://api.unsplash.com/users/r3dmax
             * html : http://unsplash.com/@r3dmax
             * photos : https://api.unsplash.com/users/r3dmax/photos
             * likes : https://api.unsplash.com/users/r3dmax/likes
             * portfolio : https://api.unsplash.com/users/r3dmax/portfolio
             * following : https://api.unsplash.com/users/r3dmax/following
             * followers : https://api.unsplash.com/users/r3dmax/followers
             */

            @SerializedName("self")
            public String self;
            @SerializedName("html")
            public String html;
            @SerializedName("photos")
            public String photos;
            @SerializedName("likes")
            public String likes;
            @SerializedName("portfolio")
            public String portfolio;
            @SerializedName("following")
            public String following;
            @SerializedName("followers")
            public String followers;

            public static LinksEntityX objectFromData(String str) {

                return new Gson().fromJson(str, LinksEntityX.class);
            }
        }
    }
}
