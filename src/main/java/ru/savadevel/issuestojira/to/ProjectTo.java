package ru.savadevel.issuestojira.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Проект
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectTo {

    @XmlElement(name = "projectKey")
    @NonNull
    private String key;

    @XmlElementWrapper
    @XmlElement(name="issue")
    private List<IssueTo> issues;

    @XmlElementWrapper
    @XmlElement(name="description")
    private List<DescriptionTo> descriptions;
}
