package br.com.meinz.agentms.model;

import br.com.meinz.agentms.enums.FunctionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "function")
@NoArgsConstructor
@AllArgsConstructor
public class Function extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "flow_id", nullable = false)
    private Flow flow;

    @Column(name = "expected_output", length = 16777216)
    @Lob
    private String expectedOutput;

    @Column(name = "expected_input", length = 16777216)
    @Lob
    private String expectedInput;

    @Column(name = "function_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FunctionType functionType;

    @Column(name = "order", nullable = false)
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "next_function_id")
    private Function next_function;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer()
                .getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Function function = (Function) object;
        return getId() != null && Objects.equals(getId(), function.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
