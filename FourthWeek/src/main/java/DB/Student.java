package DB;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private String surname;
    private String middle_name;
    private Date birthdate; // java.util.Date
    private String iin;
    private int is_grant;
    private String specialty;

    public Student() {
    }

    public Student(Long id, String name, String surname, String middle_name, Date birthdate, String iin, int is_grant, String specialty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.birthdate = birthdate;
        this.iin = iin;
        this.is_grant = is_grant;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public int isGrant() {
        return is_grant;
    }

    public void setGrant(int grant) {
        is_grant = grant;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
