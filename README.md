# trajectile

Clojure tracing library, lets you detect hotspots without necessarily having to fire up a profiler.

## Usage

In your project.clj:

    [trajectile "0.0.2"]

Example code:

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

I wrote this because I'm fucking lazy, not because I'm smart.

Should remind you of [Ruby's tracer_bullets](https://github.com/n8/tracer_bullets) and [Python's rubber_bullets](https://github.com/bclune/rubber_bullets) somewhat.

## Performance notes

It's good but not LMAX disruptor good. JVM warm-up can affect the timings so don't do anything silly. Using something like Hugo Duncan's criterium before tracing wouldn't be the worst idea.

That aside, this is more of a production and staging tracing library than a super precise benchmarking thing.

## Future plans

Distributed repeaters and aggregators (Riemann, Kafka, Zipkin, Scribe, Hadoop, statsd)

Auto-decorator macro magic for tracing the contents of functions.

Multi-dimensional tracing.

## License

Copyright © 2013 Chris Allen

Distributed under the Eclipse Public License, the same as Clojure.
