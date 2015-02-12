package com.cooldatasoft.test;

import java.util.LinkedList;
import java.util.List;

import org.mockito.internal.listeners.CollectCreatedMocks;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;

/**
 * Created by fmucar on 29/01/15.
 * <p/>
 * Used to collect all the mocks created for a test class and verify call on those mocks.
 * <p/>
 * verifying noMoreInteractions with mocks in a test is easy with this class.
 * <p/>
 * Sample usage:
 * <p/>
 * <pre>
 * {@code
 * public class ATestClass {
 *   private final MocksCollector mocksCollector = new MocksCollector();
 *
 *   @Test
 *   public void someTest(){
 *       ....
 *       verifyNoMoreInteractions(mocksCollector.getMocks());
 *   }
 *
 * }
 * </pre>
 */
public class MocksCollector {

    private final List<Object> createdMocks;

    public MocksCollector() {
        createdMocks = new LinkedList<Object>();
        final MockingProgress progress = new ThreadSafeMockingProgress();
        progress.setListener(new CollectCreatedMocks(createdMocks));
    }

    public Object[] getMocks() {
        return createdMocks.toArray();
    }
}
