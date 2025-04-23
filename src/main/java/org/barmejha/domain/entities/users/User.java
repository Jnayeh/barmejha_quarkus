package org.barmejha.domain.entities.users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.enums.UserType;
import org.barmejha.domain.id.USID;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"  // matches your UserType enum field
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Client.class, name = "CLIENT"),
    @JsonSubTypes.Type(value = Admin.class, name = "ADMIN"),
    @JsonSubTypes.Type(value = Provider.class, name = "PROVIDER")
})

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Valid
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User extends AuditedEntity {

  @Id
  @USID
  private Long id;

  @Column(unique = true)
  @Email
  private String email;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  //@Column(name = "user_type")
  @Transient
  @Enumerated(EnumType.STRING)
  private UserType type;

  // Common fields for all users
  @Column(name = "user_name", unique = true)
  private String userName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  public UserType getType() {
    if (this instanceof Client) return UserType.CLIENT;
    if (this instanceof Admin) return UserType.ADMIN;
    if (this instanceof Provider) return UserType.PROVIDER;
    return null;
  }
}
