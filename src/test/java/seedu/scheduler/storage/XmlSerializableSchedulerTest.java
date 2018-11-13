package seedu.scheduler.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.commons.util.XmlUtil;

public class XmlSerializableSchedulerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlSerializableSchedulerTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsScheduler.xml");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventScheduler.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        XmlSerializableScheduler dataFromFile = XmlUtil.getDataFromFile(INVALID_EVENT_FILE,
                XmlSerializableScheduler.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

}
