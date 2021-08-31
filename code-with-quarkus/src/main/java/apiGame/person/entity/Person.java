package apiGame.person.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

    private String id;
    private String name;
    private Integer age;
}
