package network.tcp.autocloserable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");


            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        } finally {
            if (resource2 != null) {
                try {
                    resource2.closeEx();
                } catch (CloseException e) {
                    // close() 에서 발생한 예외는 버린다. 필요하면 로깅 정도만
                    System.out.println("close ex: " + e);
                }
            }

            if (resource1 != null) {
                try {
                    resource1.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }
    }
}

// 해결 방안으로 finally 안에 또 try-catch 도입
// - close() 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 해결
// - 핵심 예외가 finally 에서 발생한 부가 예외로 바뀌어 버리는 문제 해결

// 핵심적인 문제들은 해결되었지만 코드 부분에서 보면 아쉬운 부분이 많다.
// 이런 문제를 한번에 해결하는 것이 바로 자바 중급1편에서 학습한 try-with-resources 구문이다.
