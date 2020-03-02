package com.worldremit.sousers.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Auto generated data class from Json.
 */
public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("account_id")
    @Expose
    private Integer accountId;
    @SerializedName("is_employee")
    @Expose
    private Boolean isEmployee;
    @SerializedName("last_modified_date")
    @Expose
    private Integer lastModifiedDate;
    @SerializedName("last_access_date")
    @Expose
    private Integer lastAccessDate;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("reputation_change_year")
    @Expose
    private Integer reputationChangeYear;
    @SerializedName("reputation_change_quarter")
    @Expose
    private Integer reputationChangeQuarter;
    @SerializedName("reputation_change_month")
    @Expose
    private Integer reputationChangeMonth;
    @SerializedName("reputation_change_week")
    @Expose
    private Integer reputationChangeWeek;
    @SerializedName("reputation_change_day")
    @Expose
    private Integer reputationChangeDay;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("accept_rate")
    @Expose
    private Integer acceptRate;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    private boolean isFollowed;

    private boolean isBlocked;

    public User() {
    }

    protected User(Parcel in) {
        this.accountId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isEmployee = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.lastModifiedDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.lastAccessDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputationChangeYear = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputationChangeQuarter = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputationChangeMonth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputationChangeWeek = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputationChangeDay = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reputation = (Integer) in.readValue(Integer.class.getClassLoader());
        this.creationDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userType = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.acceptRate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.location = in.readString();
        this.websiteUrl = in.readString();
        this.link = in.readString();
        this.profileImage = in.readString();
        this.displayName = in.readString();
        this.isFollowed = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isBlocked = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Integer getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Integer lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Integer lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getReputationChangeYear() {
        return reputationChangeYear;
    }

    public void setReputationChangeYear(Integer reputationChangeYear) {
        this.reputationChangeYear = reputationChangeYear;
    }

    public Integer getReputationChangeQuarter() {
        return reputationChangeQuarter;
    }

    public void setReputationChangeQuarter(Integer reputationChangeQuarter) {
        this.reputationChangeQuarter = reputationChangeQuarter;
    }

    public Integer getReputationChangeMonth() {
        return reputationChangeMonth;
    }

    public void setReputationChangeMonth(Integer reputationChangeMonth) {
        this.reputationChangeMonth = reputationChangeMonth;
    }

    public Integer getReputationChangeWeek() {
        return reputationChangeWeek;
    }

    public void setReputationChangeWeek(Integer reputationChangeWeek) {
        this.reputationChangeWeek = reputationChangeWeek;
    }

    public Integer getReputationChangeDay() {
        return reputationChangeDay;
    }

    public void setReputationChangeDay(Integer reputationChangeDay) {
        this.reputationChangeDay = reputationChangeDay;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(Integer acceptRate) {
        this.acceptRate = acceptRate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(accountId, user.accountId) &&
                Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountId, userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.accountId);
        dest.writeValue(this.isEmployee);
        dest.writeValue(this.lastModifiedDate);
        dest.writeValue(this.lastAccessDate);
        dest.writeValue(this.age);
        dest.writeValue(this.reputationChangeYear);
        dest.writeValue(this.reputationChangeQuarter);
        dest.writeValue(this.reputationChangeMonth);
        dest.writeValue(this.reputationChangeWeek);
        dest.writeValue(this.reputationChangeDay);
        dest.writeValue(this.reputation);
        dest.writeValue(this.creationDate);
        dest.writeString(this.userType);
        dest.writeValue(this.userId);
        dest.writeValue(this.acceptRate);
        dest.writeString(this.location);
        dest.writeString(this.websiteUrl);
        dest.writeString(this.link);
        dest.writeString(this.profileImage);
        dest.writeString(this.displayName);
        dest.writeValue(this.isFollowed);
        dest.writeValue(this.isBlocked);
    }
}
