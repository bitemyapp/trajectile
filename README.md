# trajectile

Clojure tracing library, lets you detect hotspots without necessarily having to fire up a profiler.

## Usage

    (defn sleep-twice-and-return [time]
      (trace :begin)
      (Thread/sleep time)
      (Thread/sleep time)
      (trace :end))
It'll log the interval between :begin and :end using Timbre.

Should look something like:

    2013-Aug-03 21:29:53 -0700 lamia TRACE [trajectile.core] - Last trace - :begin Time since - 201 New trace - :end

Telling you (in order) that:
  * The last trace keyword was :begin
  * It's been 201 milliseconds since :begin
  * The new (current) trace keyword is :end

## License

Copyright © 2013 Chris Allen

Distributed under the Eclipse Public License, the same as Clojure.
