package network.tcp.autocloserable;

public class ResourceCloseMainV2 {

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
                resource2.closeEx();    // finally 에서 한 번 더 예외가 터짐
            }

            if (resource1 != null) {
                resource1.closeEx();    // 그래서 이 코드가 호출이 안됨
            }
        }
    }
}

// null 체크
// 만약 resource2 객체를 생성하기 전에 예외가 발생하면 resource2 는 null 이 된다. 따라서 null 체크를 해야 한다.
// resource1 의 경우에도 resource1 을 생성하는 중에 예외가 발생한다면 null 체크가 필요하다.

// 자원 정리중에 예외가 발생하는 문제
// 자원을 정리하는 중에 finally 코드 블록 안에서 resource2.closeEx() 를 호출하면서 예외가 발생한다.
// 결과적으로 resource1.closeEx()는 호출되지 않는다

// 핵심 예외가 바뀌는 문제
// 이 경우 logic() 을 호출한 쪽에서는 핵심 예외인 CallException 이 아니라 finally 블록에서 새로 생성된 CloseException 을 받게 된다.
// 핵심 예외가 사라진 것이다!

