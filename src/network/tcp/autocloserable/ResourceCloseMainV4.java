package network.tcp.autocloserable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }

            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (
                ResourceV2 resource1 = new ResourceV2("resource1");
                ResourceV2 resource2 = new ResourceV2("resource2")) {

            resource1.call();
            resource2.callEx(); // CallException

        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        }
    }
}

// try-with-resources 는 단순하게 close() 를 자동 호출해준다는 정도의 기능한 제공하는 것이 아니다.
// 고민한 6가지 문제를 모두 해결하는 장치이다.

// 2가지 핵심 문제
// close() 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
// finally 블럭 안에서 자원을 닫을 때 예외가 발생하면, 핵심 예외가 finally 에서 발생한 부가 예외로 바뀌어 버린다. 그리고 핵심 예외가 사라진다.

// 4가지 부가 문제
// resource 변수를 선언하면서 동시에 할당할 수 없음( try , finally 코드 블록과 변수 스코프가 다른 문제)
// catch 이후에 finally 호출, 자원 정리가 조금 늦어진다.
// 개발자가 실수로 close() 를 호출하지 않을 가능성
// 개발자가 close() 호출 순서를 실수, 보통 자원을 생성한 순서와 반대로 닫아야 함

// Try with resources 예외 처리와 부가 예외 포함
// try-with-resources 는 핵심 예외를 반환한다.
// 부가 예외는 핵심 예외안에 Suppressed 로 담아서 반환한다.
// 개발자는 자원 정리 중에 발생한 부가 예외를 e.getSuppressed() 를 통해 활용할 수 있다.
