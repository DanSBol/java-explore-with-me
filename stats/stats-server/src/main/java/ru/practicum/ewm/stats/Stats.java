package ru.practicum.ewm.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.stats.ShortStatsDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SqlResultSetMapping(
        name = "statsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ShortStatsDto.class,
                        columns = {
                                @ColumnResult(name = "app", type = String.class),
                                @ColumnResult(name = "uri", type = String.class),
                                @ColumnResult(name = "hits", type = Integer.class)
                        }
                )
        }
)
@NamedNativeQuery(
        name = "Stats.viewStats",
        query = "select st.app as app, st.uri as uri, count(*) as hits " +
                "from stats as st " +
                "where st.datetime >= ?1 and st.datetime <= ?2 " +
                "group by app, uri " +
                "order by hits DESC",
        resultSetMapping = "statsMapping"
)
@NamedNativeQuery(
        name = "Stats.viewStatsUris",
        query = "select st.app as app, st.uri as uri, count(*) as hits " +
                "from stats as st " +
                "where st.datetime >= ?1 and st.datetime <= ?2 and uri IN ?3 " +
                "group by app, uri " +
                "order by hits DESC",
        resultSetMapping = "statsMapping"
)
@NamedNativeQuery(
        name = "Stats.viewStatsUnique",
        query = "select st.app as app, st.uri as uri, count(*) as hits " +
                "from " +
                "(select st.app as app, st.uri as uri, st.ip as ip " +
                "from stats as st " +
                "where st.datetime >= ?1 and st.datetime <= ?2 " +
                "group by app, uri, ip) as st " +
                "group by app, uri " +
                "order by hits DESC",
        resultSetMapping = "statsMapping"
)
@NamedNativeQuery(
        name = "Stats.viewStatsUrisUnique",
        query = "select st.app as app, st.uri as uri, count(*) as hits " +
                "from " +
                "(select st.app as app, st.uri as uri, st.ip as ip " +
                "from stats as st " +
                "where st.datetime >= ?1 and st.datetime <= ?2 and uri IN ?3 " +
                "group by app, uri, ip) as st " +
                "group by app, uri " +
                "order by hits DESC",
        resultSetMapping = "statsMapping"
)
@Table(name = "stats")
@EqualsAndHashCode
@Builder(builderClassName = "StatsBuilder")
public class Stats implements Serializable {
    private static final long serialVersionUID = 1224483473794225719L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String app;

    @NotNull
    @Column(nullable = false)
    private String uri;

    @NotNull
    @Column(nullable = false)
    private String ip;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime datetime;

    public static class StatsBuilder {
        public StatsBuilder() {
            // Пустой конструктор
        }
    }
}