package me.minesuchtiiii.trollboss.trolls;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum TrollType {

    ABDUCT(TrollFlag.PREVENT_GROUND_MOVEMENT, TrollFlag.PREVENT_MINING),
    ANVIL,
    BADAPPLE,
    BOLT,
    BOOM,
    BORDER,
    BURN,
    BURY,
    CRASH,
    CREEPER,
    DENYMOVE(TrollFlag.PREVENT_ALL_MOVEMENT),
    DROPINV,
    DRUG,
    FAKEDEOP,
    FAKEOP,
    FAKERESTART,
    FREEFALL,
    FREEZE,
    GARBAGE,
    GOKILL,
    HEROBRINE,
    HURT,
    INFECT,
    INVTEXT,
    LAUNCH,
    NOMINE(TrollFlag.PREVENT_MINING),
    NOOB(TrollFlag.PREVENT_ALL_MOVEMENT),
    POPULAR,
    POPUP,
    POTATOTROLL,
    PUMPKINHEAD,
    PUSH,
    RANDOMTP,
    RUNFORREST,
    SCHLONG,
    SKY(TrollFlag.PREVENT_GROUND_MOVEMENT),
    SPAM,
    SPANK,
    SPARTA,
    SQUIDRAIN,
    STARVE,
    STFU(TrollFlag.PREVENT_CHAT),
    TRAMPLE,
    TRAP(TrollFlag.PREVENT_MINING),
    TROLLKICK,
    TURN,
    VOID(TrollFlag.PREVENT_GROUND_MOVEMENT),
    WEBTRAP;

    private final Set<TrollFlag> flags;

    TrollType(TrollFlag... flags) {
        if (flags.length == 0) {
            this.flags = Collections.emptySet();
        }
        else {
            this.flags = EnumSet.of(flags[0], flags);
        }
    }

    public boolean hasFlag(TrollFlag flag) {
        return flags.contains(flag);
    }

}
