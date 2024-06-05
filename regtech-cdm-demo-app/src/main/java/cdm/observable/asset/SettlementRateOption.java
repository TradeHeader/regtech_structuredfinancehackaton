package cdm.observable.asset;

import cdm.observable.asset.PriceSourceDisruption;
import cdm.observable.asset.SettlementRateOption;
import cdm.observable.asset.SettlementRateOption.SettlementRateOptionBuilder;
import cdm.observable.asset.SettlementRateOption.SettlementRateOptionBuilderImpl;
import cdm.observable.asset.SettlementRateOption.SettlementRateOptionImpl;
import cdm.observable.asset.SettlementRateOptionEnum;
import cdm.observable.asset.meta.SettlementRateOptionMeta;
import cdm.observable.asset.metafields.FieldWithMetaSettlementRateOptionEnum;
import cdm.observable.asset.metafields.FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder;
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
 * Defines the settlement rate option to use for fixing in case of cash settlement. Currently only applicable to foreign exchange fixing in case of cross-currency settlement.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementRateOption", builder=SettlementRateOption.SettlementRateOptionBuilderImpl.class, version="${project.version}")
public interface SettlementRateOption extends RosettaModelObject {

	SettlementRateOptionMeta metaData = new SettlementRateOptionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The rate source for the conversion to the settlement currency. This source is specified through a scheme that reflects the terms of the Annex A to the 1998 FX and Currency Option Definitions.
	 */
	FieldWithMetaSettlementRateOptionEnum getSettlementRateOption();
	/**
	 * An attribute defining the parameters to get a new quote when a settlement rate option is disrupted.
	 */
	PriceSourceDisruption getPriceSourceDisruption();

	/*********************** Build Methods  ***********************/
	SettlementRateOption build();
	
	SettlementRateOption.SettlementRateOptionBuilder toBuilder();
	
	static SettlementRateOption.SettlementRateOptionBuilder builder() {
		return new SettlementRateOption.SettlementRateOptionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementRateOption> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementRateOption> getType() {
		return SettlementRateOption.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("settlementRateOption"), processor, FieldWithMetaSettlementRateOptionEnum.class, getSettlementRateOption());
		processRosetta(path.newSubPath("priceSourceDisruption"), processor, PriceSourceDisruption.class, getPriceSourceDisruption());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementRateOptionBuilder extends SettlementRateOption, RosettaModelObjectBuilder {
		FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getOrCreateSettlementRateOption();
		FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getSettlementRateOption();
		PriceSourceDisruption.PriceSourceDisruptionBuilder getOrCreatePriceSourceDisruption();
		PriceSourceDisruption.PriceSourceDisruptionBuilder getPriceSourceDisruption();
		SettlementRateOption.SettlementRateOptionBuilder setSettlementRateOption(FieldWithMetaSettlementRateOptionEnum settlementRateOption0);
		SettlementRateOption.SettlementRateOptionBuilder setSettlementRateOptionValue(SettlementRateOptionEnum settlementRateOption1);
		SettlementRateOption.SettlementRateOptionBuilder setPriceSourceDisruption(PriceSourceDisruption priceSourceDisruption);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("settlementRateOption"), processor, FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder.class, getSettlementRateOption());
			processRosetta(path.newSubPath("priceSourceDisruption"), processor, PriceSourceDisruption.PriceSourceDisruptionBuilder.class, getPriceSourceDisruption());
		}
		

		SettlementRateOption.SettlementRateOptionBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementRateOption  ***********************/
	class SettlementRateOptionImpl implements SettlementRateOption {
		private final FieldWithMetaSettlementRateOptionEnum settlementRateOption;
		private final PriceSourceDisruption priceSourceDisruption;
		
		protected SettlementRateOptionImpl(SettlementRateOption.SettlementRateOptionBuilder builder) {
			this.settlementRateOption = ofNullable(builder.getSettlementRateOption()).map(f->f.build()).orElse(null);
			this.priceSourceDisruption = ofNullable(builder.getPriceSourceDisruption()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("settlementRateOption")
		public FieldWithMetaSettlementRateOptionEnum getSettlementRateOption() {
			return settlementRateOption;
		}
		
		@Override
		@RosettaAttribute("priceSourceDisruption")
		public PriceSourceDisruption getPriceSourceDisruption() {
			return priceSourceDisruption;
		}
		
		@Override
		public SettlementRateOption build() {
			return this;
		}
		
		@Override
		public SettlementRateOption.SettlementRateOptionBuilder toBuilder() {
			SettlementRateOption.SettlementRateOptionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementRateOption.SettlementRateOptionBuilder builder) {
			ofNullable(getSettlementRateOption()).ifPresent(builder::setSettlementRateOption);
			ofNullable(getPriceSourceDisruption()).ifPresent(builder::setPriceSourceDisruption);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementRateOption _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateOption, _that.getSettlementRateOption())) return false;
			if (!Objects.equals(priceSourceDisruption, _that.getPriceSourceDisruption())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateOption != null ? settlementRateOption.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (priceSourceDisruption != null ? priceSourceDisruption.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementRateOption {" +
				"settlementRateOption=" + this.settlementRateOption + ", " +
				"priceSourceDisruption=" + this.priceSourceDisruption +
			'}';
		}
	}

	/*********************** Builder Implementation of SettlementRateOption  ***********************/
	class SettlementRateOptionBuilderImpl implements SettlementRateOption.SettlementRateOptionBuilder {
	
		protected FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder settlementRateOption;
		protected PriceSourceDisruption.PriceSourceDisruptionBuilder priceSourceDisruption;
	
		public SettlementRateOptionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementRateOption")
		public FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getSettlementRateOption() {
			return settlementRateOption;
		}
		
		@Override
		public FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getOrCreateSettlementRateOption() {
			FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder result;
			if (settlementRateOption!=null) {
				result = settlementRateOption;
			}
			else {
				result = settlementRateOption = FieldWithMetaSettlementRateOptionEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceSourceDisruption")
		public PriceSourceDisruption.PriceSourceDisruptionBuilder getPriceSourceDisruption() {
			return priceSourceDisruption;
		}
		
		@Override
		public PriceSourceDisruption.PriceSourceDisruptionBuilder getOrCreatePriceSourceDisruption() {
			PriceSourceDisruption.PriceSourceDisruptionBuilder result;
			if (priceSourceDisruption!=null) {
				result = priceSourceDisruption;
			}
			else {
				result = priceSourceDisruption = PriceSourceDisruption.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("settlementRateOption")
		public SettlementRateOption.SettlementRateOptionBuilder setSettlementRateOption(FieldWithMetaSettlementRateOptionEnum settlementRateOption) {
			this.settlementRateOption = settlementRateOption==null?null:settlementRateOption.toBuilder();
			return this;
		}
		@Override
		public SettlementRateOption.SettlementRateOptionBuilder setSettlementRateOptionValue(SettlementRateOptionEnum settlementRateOption) {
			this.getOrCreateSettlementRateOption().setValue(settlementRateOption);
			return this;
		}
		@Override
		@RosettaAttribute("priceSourceDisruption")
		public SettlementRateOption.SettlementRateOptionBuilder setPriceSourceDisruption(PriceSourceDisruption priceSourceDisruption) {
			this.priceSourceDisruption = priceSourceDisruption==null?null:priceSourceDisruption.toBuilder();
			return this;
		}
		
		@Override
		public SettlementRateOption build() {
			return new SettlementRateOption.SettlementRateOptionImpl(this);
		}
		
		@Override
		public SettlementRateOption.SettlementRateOptionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementRateOption.SettlementRateOptionBuilder prune() {
			if (settlementRateOption!=null && !settlementRateOption.prune().hasData()) settlementRateOption = null;
			if (priceSourceDisruption!=null && !priceSourceDisruption.prune().hasData()) priceSourceDisruption = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSettlementRateOption()!=null) return true;
			if (getPriceSourceDisruption()!=null && getPriceSourceDisruption().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementRateOption.SettlementRateOptionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettlementRateOption.SettlementRateOptionBuilder o = (SettlementRateOption.SettlementRateOptionBuilder) other;
			
			merger.mergeRosetta(getSettlementRateOption(), o.getSettlementRateOption(), this::setSettlementRateOption);
			merger.mergeRosetta(getPriceSourceDisruption(), o.getPriceSourceDisruption(), this::setPriceSourceDisruption);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementRateOption _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateOption, _that.getSettlementRateOption())) return false;
			if (!Objects.equals(priceSourceDisruption, _that.getPriceSourceDisruption())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateOption != null ? settlementRateOption.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (priceSourceDisruption != null ? priceSourceDisruption.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementRateOptionBuilder {" +
				"settlementRateOption=" + this.settlementRateOption + ", " +
				"priceSourceDisruption=" + this.priceSourceDisruption +
			'}';
		}
	}
}
