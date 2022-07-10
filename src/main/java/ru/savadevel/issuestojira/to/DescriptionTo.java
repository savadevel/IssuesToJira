package ru.savadevel.issuestojira.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DescriptionTo {

    @XmlElement(name = "id")
    @NonNull
    private Integer id;

    @XmlElement(name = "summary")
    private String summary;
}
