package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class PostDTO {

  private Long id;

  private CommunityDTO community;

  private UserDTO author;

  private String title;

  private String content;

  private List<CommentDTO> comments;
}
