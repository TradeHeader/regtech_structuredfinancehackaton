package cdm.product.common.settlement;

import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.QuantityMultiplier.QuantityMultiplierBuilder;
import cdm.product.common.settlement.QuantityMultiplier.QuantityMultiplierBuilderImpl;
import cdm.product.common.settlement.QuantityMultiplier.QuantityMultiplierImpl;
import cdm.product.common.settlement.meta.QuantityMultiplierMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  Class to specify a mechanism for a quantity to be set as a multiplier to another (reference) quantity, based on a price observation. At the moment this class only supports FX or Equity-linked notional and re-uses existing building blocks for those 2 cases, until such time when component can be made more generic. This captures the case of resetting cross-currency swaps and resetting equity swaps.
 * @version ${project.version}
 */
@RosettaDataType(value="QuantityMultiplier", builder=QuantityMultiplier.QuantityMultiplierBuilderImpl.class, version="${project.version}")
public interface QuantityMultiplier extends RosettaModelObject {

	QuantityMultiplierMeta metaData = new QuantityMultiplierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Multiplier specified as an FX-linked schedule, e.g. for a resetting cross-currency swap..
	 */
	FxLinkedNotionalSchedule getFxLinkedNotionalSchedule();
	BigDecimal getMultiplierValue();

	/*********************** Build Methods  ***********************/
	QuantityMultiplier build();
	
	QuantityMultiplier.QuantityMultiplierBuilder toBuilder();
	
	static QuantityMultiplier.QuantityMultiplierBuilder builder() {
		return new QuantityMultiplier.QuantityMultiplierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends QuantityMultiplier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends QuantityMultiplier> getType() {
		return QuantityMultiplier.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("fxLinkedNotionalSchedule"), processor, FxLinkedNotionalSchedule.class, getFxLinkedNotionalSchedule());
		processor.processBasic(path.newSubPath("multiplierValue"), BigDecimal.class, getMultiplierValue(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface QuantityMultiplierBuilder extends QuantityMultiplier, RosettaModelObjectBuilder {
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder getOrCreateFxLinkedNotionalSchedule();
		FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder getFxLinkedNotionalSchedule();
		QuantityMultiplier.QuantityMultiplierBuilder setFxLinkedNotionalSchedule(FxLinkedNotionalSchedule fxLinkedNotionalSchedule);
		QuantityMultiplier.QuantityMultiplierBuilder setMultiplierValue(BigDecimal multiplierValue);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("fxLinkedNotionalSchedule"), processor, FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder.class, getFxLinkedNotionalSchedule());
			processor.processBasic(path.newSubPath("multiplierValue"), BigDecimal.class, getMultiplierValue(), this);
		}
		

		QuantityMultiplier.QuantityMultiplierBuilder prune();
	}

	/*********************** Immutable Implementation of QuantityMultiplier  ***********************/
	class QuantityMultiplierImpl implements QuantityMultiplier {
		private final FxLinkedNotionalSchedule fxLinkedNotionalSchedule;
		private final BigDecimal multiplierValue;
		
		protected QuantityMultiplierImpl(QuantityMultiplier.QuantityMultiplierBuilder builder) {
			this.fxLinkedNotionalSchedule = ofNullable(builder.getFxLinkedNotionalSchedule()).map(f->f.build()).orElse(null);
			this.multiplierValue = builder.getMultiplierValue();
		}
		
		@Override
		@RosettaAttribute("fxLinkedNotionalSchedule")
		public FxLinkedNotionalSchedule getFxLinkedNotionalSchedule() {
			return fxLinkedNotionalSchedule;
		}
		
		@Override
		@RosettaAttribute("multiplierValue")
		public BigDecimal getMultiplierValue() {
			return multiplierValue;
		}
		
		@Override
		public QuantityMultiplier build() {
			return this;
		}
		
		@Override
		public QuantityMultiplier.QuantityMultiplierBuilder toBuilder() {
			QuantityMultiplier.QuantityMultiplierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(QuantityMultiplier.QuantityMultiplierBuilder builder) {
			ofNullable(getFxLinkedNotionalSchedule()).ifPresent(builder::setFxLinkedNotionalSchedule);
			ofNullable(getMultiplierValue()).ifPresent(builder::setMultiplierValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuantityMultiplier _that = getType().cast(o);
		
			if (!Objects.equals(fxLinkedNotionalSchedule, _that.getFxLinkedNotionalSchedule())) return false;
			if (!Objects.equals(multiplierValue, _that.getMultiplierValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxLinkedNotionalSchedule != null ? fxLinkedNotionalSchedule.hashCode() : 0);
			_result = 31 * _result + (multiplierValue != null ? multiplierValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuantityMultiplier {" +
				"fxLinkedNotionalSchedule=" + this.fxLinkedNotionalSchedule + ", " +
				"multiplierValue=" + this.multiplierValue +
			'}';
		}
	}

	/*********************** Builder Implementation of QuantityMultiplier  ***********************/
	class QuantityMultiplierBuilderImpl implements QuantityMultiplier.QuantityMultiplierBuilder {
	
		protected FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder fxLinkedNotionalSchedule;
		protected BigDecimal multiplierValue;
	
		public QuantityMultiplierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fxLinkedNotionalSchedule")
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder getFxLinkedNotionalSchedule() {
			return fxLinkedNotionalSchedule;
		}
		
		@Override
		public FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder getOrCreateFxLinkedNotionalSchedule() {
			FxLinkedNotionalSchedule.FxLinkedNotionalScheduleBuilder result;
			if (fxLinkedNotionalSchedule!=null) {
				result = fxLinkedNotionalSchedule;
			}
			else {
				result = fxLinkedNotionalSchedule = FxLinkedNotionalSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("multiplierValue")
		public BigDecimal getMultiplierValue() {
			return multiplierValue;
		}
		
	
		@Override
		@RosettaAttribute("fxLinkedNotionalSchedule")
		public QuantityMultiplier.QuantityMultiplierBuilder setFxLinkedNotionalSchedule(FxLinkedNotionalSchedule fxLinkedNotionalSchedule) {
			this.fxLinkedNotionalSchedule = fxLinkedNotionalSchedule==null?null:fxLinkedNotionalSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("multiplierValue")
		public QuantityMultiplier.QuantityMultiplierBuilder setMultiplierValue(BigDecimal multiplierValue) {
			this.multiplierValue = multiplierValue==null?null:multiplierValue;
			return this;
		}
		
		@Override
		public QuantityMultiplier build() {
			return new QuantityMultiplier.QuantityMultiplierImpl(this);
		}
		
		@Override
		public QuantityMultiplier.QuantityMultiplierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuantityMultiplier.QuantityMultiplierBuilder prune() {
			if (fxLinkedNotionalSchedule!=null && !fxLinkedNotionalSchedule.prune().hasData()) fxLinkedNotionalSchedule = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFxLinkedNotionalSchedule()!=null && getFxLinkedNotionalSchedule().hasData()) return true;
			if (getMultiplierValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuantityMultiplier.QuantityMultiplierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			QuantityMultiplier.QuantityMultiplierBuilder o = (QuantityMultiplier.QuantityMultiplierBuilder) other;
			
			merger.mergeRosetta(getFxLinkedNotionalSchedule(), o.getFxLinkedNotionalSchedule(), this::setFxLinkedNotionalSchedule);
			
			merger.mergeBasic(getMultiplierValue(), o.getMultiplierValue(), this::setMultiplierValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuantityMultiplier _that = getType().cast(o);
		
			if (!Objects.equals(fxLinkedNotionalSchedule, _that.getFxLinkedNotionalSchedule())) return false;
			if (!Objects.equals(multiplierValue, _that.getMultiplierValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxLinkedNotionalSchedule != null ? fxLinkedNotionalSchedule.hashCode() : 0);
			_result = 31 * _result + (multiplierValue != null ? multiplierValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuantityMultiplierBuilder {" +
				"fxLinkedNotionalSchedule=" + this.fxLinkedNotionalSchedule + ", " +
				"multiplierValue=" + this.multiplierValue +
			'}';
		}
	}
}
