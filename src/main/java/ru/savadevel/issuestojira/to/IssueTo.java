package ru.savadevel.issuestojira.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Задача
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueTo {

    @XmlElement(name = "id")
    @NonNull
    private Integer id;

    @XmlElement(name = "type")
    private TypeTo type;

    @XmlElement(name = "priority")
    private PriorityTo priority;
}

