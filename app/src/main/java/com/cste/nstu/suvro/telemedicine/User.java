package com.cste.nstu.suvro.telemedicine;


public class User {

        private int id;
        private String name;
        private String email;
        private String gender;
        private int age;
        private String skypeName;
        private String mobile;

        public User(int id, String name, String email, String gender,
                       int age, String skypeName,String mobile) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.gender = gender;
            this.age = age;
            this.skypeName=skypeName;
            this.mobile = mobile;
        }
        public User(String name, String email, String gender, int age, String skypeName,String mobile)
        {
            this.name = name;
            this.email = email;
            this.gender = gender;
            this.age = age;
            this.skypeName=skypeName;
            this.mobile = mobile;
        }
        public User() {

        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
        this.gender = gender;
       }

       public int getAge() {
           return age;
       }

       public void setAge(Integer age) {
            this.age = age;
        }
        public String getSkypeName() {
        return skypeName;
        }
        public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
        }
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String toString() {


            return " user_id=" + id + "\n user_name=" + name + "\n user_email=" + email
                    + "\n gender=" + gender + "\n age=" + age + "\n skypeName=" + skypeName + "\n user_phoneNo="
                    + mobile ;
        }


        public int getString() {
            return 0;
        }
    }


