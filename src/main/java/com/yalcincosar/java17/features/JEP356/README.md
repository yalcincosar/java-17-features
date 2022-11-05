Description

We provide a new interface, RandomGenerator, which supplies a uniform API for all existing and new PRNGs. RandomGenerators provide methods named ints, longs, doubles, nextBoolean, nextInt, nextLong, nextDouble, and nextFloat, with all their current parameter variations.

We provide four new specialized RandomGenerator interfaces:

    SplittableRandomGenerator extends RandomGenerator and also provides
    methods named split and splits. Splittability allows the user to spawn a new RandomGenerator from an existing RandomGenerator that will generally produce statistically independent results.

    JumpableRandomGenerator extendsRandomGenerator and also provides
    methods named jump and jumps. Jumpability allows a user to jump ahead a moderate number of draws.

    LeapableRandomGenerator extends RandomGenerator and also provides
    methods named leap and leaps. Leapability allows a user to jump ahead a large number of draws.

    ArbitrarilyJumpableRandomGenerator extends LeapableRandomGenerator and also provides additional variations of jump and jumps that allow an arbitrary jump distance to be specified.

We provide a new class RandomGeneratorFactory which is used to locate and construct instances of RandomGenerator implementations. The RandomGeneratorFactory uses the ServiceLoader.Provider API to register RandomGenerator implementations.

We have refactored Random, ThreadLocalRandom, and SplittableRandom so as to share most of their implementation code and, furthermore, make that code reusable by other algorithms as well. This refactoring creates underlying non-public abstract classes AbstractRandomGenerator, AbstractSplittableRandomGenerator, AbstractJumpableRandomGenerator, AbstractLeapableRandomGenerator, and AbstractArbitrarilyJumpableRandomGenerator, each provide only implementations for methods nextInt(), nextLong(), and (if relevant) either split(), or jump(), or jump() and leap(), or jump(distance). After this refactoring, Random, ThreadLocalRandom, and SplittableRandom inherit the RandomGenerator interface. Note that because SecureRandom is a subclass of Random, all instances of SecureRandom also automatically support the RandomGenerator interface, with no need to recode the SecureRandom class or any of its associated implementation engines.

We also added underlying non-public classes that extend AbstractSplittableRandomGenerator (and therefore implement SplittableRandomGenerator and RandomGenerator) to support six specific members of the LXM family of PRNG algorithms:

    L32X64MixRandom
    L32X64StarStarRandom
    L64X128MixRandom
    L64X128StarStarRandom
    L64X256MixRandom
    L64X1024MixRandom
    L128X128MixRandom
    L128X256MixRandom
    L128X1024MixRandom

The structure of the central nextLong (or nextInt) method of an LXM algorithm follows a suggestion in December 2017 by Sebastiano Vigna that using one LCG subgenerator and one xor-based subgenerator (rather than two LCG subgenerators) would provide a longer period, superior equidistribution, scalability, and better quality. Each of the specific implementations here combines one of the best currently known xor-based generators (xoroshiro or xoshiro, described by Blackman and Vigna in "Scrambled Linear Pseudorandom Number Generators", ACM Trans. Math. Softw., 2021) with an LCG that uses one of the best currently known multipliers (found by a search for better multipliers in 2019 by Steele and Vigna), and then applies a mixing function identified by Doug Lea. Testing has confirmed that the LXM algorithm is far superior in quality to the SplitMix algorithm (2014) used by SplittableRandom.

We also provide implementations of these widely-used PRNG algorithms:

    Xoshiro256PlusPlus
    Xoroshiro128PlusPlus

The non-public abstract implementations mentioned above may be supplied as part of a random number implementor SPI in the future.

This suite of algorithms provide Java programmers with a reasonable range of tradeoffs among space, time, quality, and compatibility with other languages.


[MORE INFO](https://openjdk.org/jeps/356)