package LLD.SplitWise.Split;

public class SplitAlgoFactory {
    
    public static SplittingAlgo getSplittingAlgo(SplitAlgoEnum type) {
        if(type == SplitAlgoEnum.EQUAL) return new EqualSplit();
        else if(type == SplitAlgoEnum.UNEQUAL) return new UnequalSplit();
        else if(type == SplitAlgoEnum.PERCENT) return new PercentSplit();
        return new EqualSplit();
    }
}
