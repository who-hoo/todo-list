package com.hooria.todo.repository;

import com.hooria.todo.domain.ActivityLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
class ActivityLogRepositoryTest {

    ActivityLogRepository repository;

    @Autowired
    ActivityLogRepositoryTest(DataSource dataSource) {
        this.repository = new ActivityLogRepository(dataSource);
    }

    @Test
    @DisplayName("인자로 주어진 활동로그를 저장소에 저장한다.")
    void insertSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        ActivityLog activityLog = new ActivityLog(1, "userId1", 1, 1, 2, now, true);

        // when
        ActivityLog result = repository.insert(activityLog);

        // then
        assertThat(result.getId()).isEqualTo(activityLog.getId());
        assertThat(result.getUserId()).isEqualTo(activityLog.getUserId());
        assertThat(result.getActivityType()).isEqualTo(activityLog.getActivityType());
        assertThat(result.getFromStatus()).isEqualTo(activityLog.getFromStatus());
        assertThat(result.getToStatus()).isEqualTo(activityLog.getToStatus());
//        assertThat(result.getCreatedAt()).isEqualTo(activityLog.getCreatedAt());
        /*
            LocalDateTime 과 같은 날짜 값 검증에 대한 팁을 알 수 있을까요? @Riako
        */
        assertThat(result.isReadYn()).isEqualTo(activityLog.isReadYn());
    }

    @Test
    @DisplayName("저장소에 저장되어 있는 모든 활동로그를 조회한다.")
    void findAllSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        List<ActivityLog> members = List.of(
                new ActivityLog(1, "userId1", 1, 1, 2, now, true),
                new ActivityLog(2, "userId2", 2, 2, 3, now, false),
                new ActivityLog(3, "userId3", 3, 1, 2, now, true)
        );

        // when
        List<ActivityLog> results = repository.findAll();

        // then
        assertThat(results.size()).isEqualTo(members.size());

        int size = results.size();
        for (int index = 0; index < size; ++index) {
            ActivityLog activityLog1 = results.get(index);
            ActivityLog activityLog2 = members.get(index);

            assertThat(activityLog1.getId()).isEqualTo(activityLog2.getId());
            assertThat(activityLog1.getUserId()).isEqualTo(activityLog2.getUserId());
            assertThat(activityLog1.getActivityType()).isEqualTo(activityLog2.getActivityType());
            assertThat(activityLog1.getFromStatus()).isEqualTo(activityLog2.getFromStatus());
            assertThat(activityLog1.getToStatus()).isEqualTo(activityLog2.getToStatus());
            assertThat(activityLog1.isReadYn()).isEqualTo(activityLog2.isReadYn());
        }
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 활동로그를 저장소에서 삭제한다.")
    void deleteByIdSuccess() {

        // given
        int id = 1;

        // when
        int resultCount = repository.deleteById(id);

        // then
        assertThat(resultCount).isNotEqualTo(0);
    }
}
