package cs.layer;

import cs.layer.*;
import java.util.*;

public class Event {
        public String tag = "新事件组";
        public int t = 1;
        public ArrayList<String> events = new ArrayList<String>();
        public ArrayList<EventRead> results = new ArrayList<EventRead>();
        public int index;
        public int loop;
        public int addtime;
        public int special;
        public Event(int idx) {
            this.index=idx;
        }
        public object Clone() {
            MemoryStream memoryStream = new MemoryStream();
            BinaryFormatter binaryFormatter = new BinaryFormatter();
            binaryFormatter.Serialize((Stream)memoryStream,(object)this);
            memoryStream.Seek(0L,SeekOrigin.Begin);
            object obj = binaryFormatter.Deserialize((Stream)memoryStream);
            memoryStream.Close();
            return obj;
        }
		}
