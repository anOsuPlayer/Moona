package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.util.IshMap;

class Core {

	protected static volatile IshMap<Serial, Long> elements = new IshMap<>();
}
