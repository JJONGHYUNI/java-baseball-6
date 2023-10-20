package baseball;

import baseball.model.Computer;
import baseball.util.BaseballNumberGenerator;
import baseball.util.BaseballRandomNumberGenerator;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 컴퓨터_스트라이크_볼_횟수_테스트(){
        //given
        Computer computer = new Computer(List.of(1,2,3));
        final List<Integer> case1 = List.of(1,2,3); //3strike
        final List<Integer> case2 = List.of(4,5,6); //0,0
        final List<Integer> case3 = List.of(1,2,6); //2strike
        final List<Integer> case4 = List.of(2,3,1); //3ball
        final List<Integer> case5 = List.of(1,5,2); //1,1

        //when
        final List<Integer> result1 = computer.compareNumberList(case1);
        final List<Integer> result2 = computer.compareNumberList(case2);
        final List<Integer> result3 = computer.compareNumberList(case3);
        final List<Integer> result4 = computer.compareNumberList(case4);
        final List<Integer> result5 = computer.compareNumberList(case5);

        //then
        assertThat(result1).isEqualTo(List.of(0,3));
        assertThat(result2).isEqualTo(List.of(0,0));
        assertThat(result3).isEqualTo(List.of(0,2));
        assertThat(result4).isEqualTo(List.of(3,0));
        assertThat(result5).isEqualTo(List.of(1,1));
    }

    @Test
    void 컴퓨터_랜덤_생성된_숫자_사이즈_중복_테스트(){
        //given
        BaseballNumberGenerator baseballNumberGenerator = new BaseballRandomNumberGenerator();
        //when
        final List<Integer> case1 = baseballNumberGenerator.generate();
        final Set<Integer> case1Set = new HashSet<>(case1);

        //then
        assertThat(case1.contains(0)).isEqualTo(false);
        assertThat(case1.size() == 3).isEqualTo(true);
        assertThat(case1.size() == case1Set.size()).isEqualTo(true);

    }
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
