package com.cste.nstu.suvro.telemedicine;


public class User {
        private int user_id;
        private String user_name ="";
        private String user_email ="";
        private String gender ="";
        private String age = "";
        private String skypeName ="";
        private String user_phoneNo;


    public User(int user_id, String user_name, String user_email, String gender, String age, String skypeName, String user_phoneNo) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.gender = gender;
        this.age = age;
        this.skypeName = skypeName;
        this.user_phoneNo = user_phoneNo;
    }
    public User( String user_name, String user_email, String gender, String age, String skypeName, String user_phoneNo) {

        this.user_name = user_name;
        this.user_email = user_email;
        this.gender = gender;
        this.age = age;
        this.skypeName = skypeName;
        this.user_phoneNo = user_phoneNo;
    }
    public  User(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public String getUser_phoneNo() {
        return user_phoneNo;
    }


    /*        public String toString() {


            return " user_id=" + id + "\n user_name=" + name + "\n user_email=" + email
                    + "\n gender=" + gender + "\n age=" + age + "\n skypeName=" + skypeName + "\n user_phoneNo="
                    + mobile ;
        }


        public int getString() {
            return 0;
        }*/

    public void setUser_phoneNo(String user_phoneNo) {
        this.user_phoneNo = user_phoneNo;
    }
    }


