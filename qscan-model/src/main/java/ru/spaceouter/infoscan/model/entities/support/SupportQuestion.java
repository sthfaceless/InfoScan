package ru.spaceouter.infoscan.model.entities.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.*;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Table(name = "support_questions")
@Data
@NoArgsConstructor
public class SupportQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_question_id", nullable = false, unique = true)
    private long supportQuestionId;

    @Column(name = "question", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String question;

    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_support_question_fk"))
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private UserEntity user;

    public SupportQuestion(String question) {
        this.question = question;
    }
}
