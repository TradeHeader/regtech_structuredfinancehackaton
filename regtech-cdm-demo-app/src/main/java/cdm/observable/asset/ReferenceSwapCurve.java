package cdm.observable.asset;

import cdm.observable.asset.MakeWholeAmount;
import cdm.observable.asset.ReferenceSwapCurve;
import cdm.observable.asset.ReferenceSwapCurve.ReferenceSwapCurveBuilder;
import cdm.observable.asset.ReferenceSwapCurve.ReferenceSwapCurveBuilderImpl;
import cdm.observable.asset.ReferenceSwapCurve.ReferenceSwapCurveImpl;
import cdm.observable.asset.SwapCurveValuation;
import cdm.observable.asset.meta.ReferenceSwapCurveMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A complex type used to specify the option and convertible bond option strike when expressed in reference to a swap curve.
 * @version ${project.version}
 */
@RosettaDataType(value="ReferenceSwapCurve", builder=ReferenceSwapCurve.ReferenceSwapCurveBuilderImpl.class, version="${project.version}")
public interface ReferenceSwapCurve extends RosettaModelObject {

	ReferenceSwapCurveMeta metaData = new ReferenceSwapCurveMeta();

	/*********************** Getter Methods  ***********************/
	SwapCurveValuation getSwapUnwindValue();
	/**
	 * Amount to be paid by the buyer of the option if the option is exercised prior to the Early Call Date. (The market practice in the convertible bond option space being that the buyer should be penalised if he/she exercises the option early on.)
	 */
	MakeWholeAmount getMakeWholeAmount();

	/*********************** Build Methods  ***********************/
	ReferenceSwapCurve build();
	
	ReferenceSwapCurve.ReferenceSwapCurveBuilder toBuilder();
	
	static ReferenceSwapCurve.ReferenceSwapCurveBuilder builder() {
		return new ReferenceSwapCurve.ReferenceSwapCurveBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceSwapCurve> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceSwapCurve> getType() {
		return ReferenceSwapCurve.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("swapUnwindValue"), processor, SwapCurveValuation.class, getSwapUnwindValue());
		processRosetta(path.newSubPath("makeWholeAmount"), processor, MakeWholeAmount.class, getMakeWholeAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceSwapCurveBuilder extends ReferenceSwapCurve, RosettaModelObjectBuilder {
		SwapCurveValuation.SwapCurveValuationBuilder getOrCreateSwapUnwindValue();
		SwapCurveValuation.SwapCurveValuationBuilder getSwapUnwindValue();
		MakeWholeAmount.MakeWholeAmountBuilder getOrCreateMakeWholeAmount();
		MakeWholeAmount.MakeWholeAmountBuilder getMakeWholeAmount();
		ReferenceSwapCurve.ReferenceSwapCurveBuilder setSwapUnwindValue(SwapCurveValuation swapUnwindValue);
		ReferenceSwapCurve.ReferenceSwapCurveBuilder setMakeWholeAmount(MakeWholeAmount makeWholeAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("swapUnwindValue"), processor, SwapCurveValuation.SwapCurveValuationBuilder.class, getSwapUnwindValue());
			processRosetta(path.newSubPath("makeWholeAmount"), processor, MakeWholeAmount.MakeWholeAmountBuilder.class, getMakeWholeAmount());
		}
		

		ReferenceSwapCurve.ReferenceSwapCurveBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceSwapCurve  ***********************/
	class ReferenceSwapCurveImpl implements ReferenceSwapCurve {
		private final SwapCurveValuation swapUnwindValue;
		private final MakeWholeAmount makeWholeAmount;
		
		protected ReferenceSwapCurveImpl(ReferenceSwapCurve.ReferenceSwapCurveBuilder builder) {
			this.swapUnwindValue = ofNullable(builder.getSwapUnwindValue()).map(f->f.build()).orElse(null);
			this.makeWholeAmount = ofNullable(builder.getMakeWholeAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("swapUnwindValue")
		public SwapCurveValuation getSwapUnwindValue() {
			return swapUnwindValue;
		}
		
		@Override
		@RosettaAttribute("makeWholeAmount")
		public MakeWholeAmount getMakeWholeAmount() {
			return makeWholeAmount;
		}
		
		@Override
		public ReferenceSwapCurve build() {
			return this;
		}
		
		@Override
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder toBuilder() {
			ReferenceSwapCurve.ReferenceSwapCurveBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceSwapCurve.ReferenceSwapCurveBuilder builder) {
			ofNullable(getSwapUnwindValue()).ifPresent(builder::setSwapUnwindValue);
			ofNullable(getMakeWholeAmount()).ifPresent(builder::setMakeWholeAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceSwapCurve _that = getType().cast(o);
		
			if (!Objects.equals(swapUnwindValue, _that.getSwapUnwindValue())) return false;
			if (!Objects.equals(makeWholeAmount, _that.getMakeWholeAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (swapUnwindValue != null ? swapUnwindValue.hashCode() : 0);
			_result = 31 * _result + (makeWholeAmount != null ? makeWholeAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceSwapCurve {" +
				"swapUnwindValue=" + this.swapUnwindValue + ", " +
				"makeWholeAmount=" + this.makeWholeAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceSwapCurve  ***********************/
	class ReferenceSwapCurveBuilderImpl implements ReferenceSwapCurve.ReferenceSwapCurveBuilder {
	
		protected SwapCurveValuation.SwapCurveValuationBuilder swapUnwindValue;
		protected MakeWholeAmount.MakeWholeAmountBuilder makeWholeAmount;
	
		public ReferenceSwapCurveBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("swapUnwindValue")
		public SwapCurveValuation.SwapCurveValuationBuilder getSwapUnwindValue() {
			return swapUnwindValue;
		}
		
		@Override
		public SwapCurveValuation.SwapCurveValuationBuilder getOrCreateSwapUnwindValue() {
			SwapCurveValuation.SwapCurveValuationBuilder result;
			if (swapUnwindValue!=null) {
				result = swapUnwindValue;
			}
			else {
				result = swapUnwindValue = SwapCurveValuation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("makeWholeAmount")
		public MakeWholeAmount.MakeWholeAmountBuilder getMakeWholeAmount() {
			return makeWholeAmount;
		}
		
		@Override
		public MakeWholeAmount.MakeWholeAmountBuilder getOrCreateMakeWholeAmount() {
			MakeWholeAmount.MakeWholeAmountBuilder result;
			if (makeWholeAmount!=null) {
				result = makeWholeAmount;
			}
			else {
				result = makeWholeAmount = MakeWholeAmount.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("swapUnwindValue")
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder setSwapUnwindValue(SwapCurveValuation swapUnwindValue) {
			this.swapUnwindValue = swapUnwindValue==null?null:swapUnwindValue.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("makeWholeAmount")
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder setMakeWholeAmount(MakeWholeAmount makeWholeAmount) {
			this.makeWholeAmount = makeWholeAmount==null?null:makeWholeAmount.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceSwapCurve build() {
			return new ReferenceSwapCurve.ReferenceSwapCurveImpl(this);
		}
		
		@Override
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder prune() {
			if (swapUnwindValue!=null && !swapUnwindValue.prune().hasData()) swapUnwindValue = null;
			if (makeWholeAmount!=null && !makeWholeAmount.prune().hasData()) makeWholeAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSwapUnwindValue()!=null && getSwapUnwindValue().hasData()) return true;
			if (getMakeWholeAmount()!=null && getMakeWholeAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceSwapCurve.ReferenceSwapCurveBuilder o = (ReferenceSwapCurve.ReferenceSwapCurveBuilder) other;
			
			merger.mergeRosetta(getSwapUnwindValue(), o.getSwapUnwindValue(), this::setSwapUnwindValue);
			merger.mergeRosetta(getMakeWholeAmount(), o.getMakeWholeAmount(), this::setMakeWholeAmount);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceSwapCurve _that = getType().cast(o);
		
			if (!Objects.equals(swapUnwindValue, _that.getSwapUnwindValue())) return false;
			if (!Objects.equals(makeWholeAmount, _that.getMakeWholeAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (swapUnwindValue != null ? swapUnwindValue.hashCode() : 0);
			_result = 31 * _result + (makeWholeAmount != null ? makeWholeAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceSwapCurveBuilder {" +
				"swapUnwindValue=" + this.swapUnwindValue + ", " +
				"makeWholeAmount=" + this.makeWholeAmount +
			'}';
		}
	}
}
