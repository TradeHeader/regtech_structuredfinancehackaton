package cdm.product.collateral;

import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption.ReferenceWithMetaFloatingRateOptionBuilder;
import cdm.product.asset.FloatingRateBase;
import cdm.product.asset.FloatingRateBase.FloatingRateBaseBuilder;
import cdm.product.asset.FloatingRateBase.FloatingRateBaseBuilderImpl;
import cdm.product.asset.FloatingRateBase.FloatingRateBaseImpl;
import cdm.product.asset.SpreadSchedule;
import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.collateral.CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder;
import cdm.product.collateral.CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilderImpl;
import cdm.product.collateral.CollateralAgreementFloatingRate.CollateralAgreementFloatingRateImpl;
import cdm.product.collateral.meta.CollateralAgreementFloatingRateMeta;
import cdm.product.template.StrikeSchedule;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Represents the parameters needed to calculate the floating rate paid on collateral holdings.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralAgreementFloatingRate", builder=CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilderImpl.class, version="${project.version}")
public interface CollateralAgreementFloatingRate extends FloatingRateBase {

	CollateralAgreementFloatingRateMeta metaData = new CollateralAgreementFloatingRateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies how negative rates should be applied.  If rates go negative, should the payment be reversed (true) or zeroed out (false)?
	 */
	Boolean getNegativeInterest();
	/**
	 * Specifies how spreads should be applied in a low/negative rate environment.  If true, spread is applied only if rate is positive.
	 */
	Boolean getCompressibleSpread();

	/*********************** Build Methods  ***********************/
	CollateralAgreementFloatingRate build();
	
	CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder toBuilder();
	
	static CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder builder() {
		return new CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralAgreementFloatingRate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralAgreementFloatingRate> getType() {
		return CollateralAgreementFloatingRate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("rateOption"), processor, ReferenceWithMetaFloatingRateOption.class, getRateOption());
		processRosetta(path.newSubPath("spreadSchedule"), processor, SpreadSchedule.class, getSpreadSchedule());
		processRosetta(path.newSubPath("capRateSchedule"), processor, StrikeSchedule.class, getCapRateSchedule());
		processRosetta(path.newSubPath("floorRateSchedule"), processor, StrikeSchedule.class, getFloorRateSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("negativeInterest"), Boolean.class, getNegativeInterest(), this);
		processor.processBasic(path.newSubPath("compressibleSpread"), Boolean.class, getCompressibleSpread(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralAgreementFloatingRateBuilder extends CollateralAgreementFloatingRate, FloatingRateBase.FloatingRateBaseBuilder, RosettaModelObjectBuilder {
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setRateOption(ReferenceWithMetaFloatingRateOption rateOption0);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setRateOptionValue(FloatingRateOption rateOption1);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setSpreadSchedule(SpreadSchedule spreadSchedule);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setCapRateSchedule(StrikeSchedule capRateSchedule);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setFloorRateSchedule(StrikeSchedule floorRateSchedule);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setMeta(MetaFields meta);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setNegativeInterest(Boolean negativeInterest);
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setCompressibleSpread(Boolean compressibleSpread);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("rateOption"), processor, ReferenceWithMetaFloatingRateOption.ReferenceWithMetaFloatingRateOptionBuilder.class, getRateOption());
			processRosetta(path.newSubPath("spreadSchedule"), processor, SpreadSchedule.SpreadScheduleBuilder.class, getSpreadSchedule());
			processRosetta(path.newSubPath("capRateSchedule"), processor, StrikeSchedule.StrikeScheduleBuilder.class, getCapRateSchedule());
			processRosetta(path.newSubPath("floorRateSchedule"), processor, StrikeSchedule.StrikeScheduleBuilder.class, getFloorRateSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("negativeInterest"), Boolean.class, getNegativeInterest(), this);
			processor.processBasic(path.newSubPath("compressibleSpread"), Boolean.class, getCompressibleSpread(), this);
		}
		

		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralAgreementFloatingRate  ***********************/
	class CollateralAgreementFloatingRateImpl extends FloatingRateBase.FloatingRateBaseImpl implements CollateralAgreementFloatingRate {
		private final Boolean negativeInterest;
		private final Boolean compressibleSpread;
		
		protected CollateralAgreementFloatingRateImpl(CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder builder) {
			super(builder);
			this.negativeInterest = builder.getNegativeInterest();
			this.compressibleSpread = builder.getCompressibleSpread();
		}
		
		@Override
		@RosettaAttribute("negativeInterest")
		public Boolean getNegativeInterest() {
			return negativeInterest;
		}
		
		@Override
		@RosettaAttribute("compressibleSpread")
		public Boolean getCompressibleSpread() {
			return compressibleSpread;
		}
		
		@Override
		public CollateralAgreementFloatingRate build() {
			return this;
		}
		
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder toBuilder() {
			CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getNegativeInterest()).ifPresent(builder::setNegativeInterest);
			ofNullable(getCompressibleSpread()).ifPresent(builder::setCompressibleSpread);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CollateralAgreementFloatingRate _that = getType().cast(o);
		
			if (!Objects.equals(negativeInterest, _that.getNegativeInterest())) return false;
			if (!Objects.equals(compressibleSpread, _that.getCompressibleSpread())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (negativeInterest != null ? negativeInterest.hashCode() : 0);
			_result = 31 * _result + (compressibleSpread != null ? compressibleSpread.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralAgreementFloatingRate {" +
				"negativeInterest=" + this.negativeInterest + ", " +
				"compressibleSpread=" + this.compressibleSpread +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CollateralAgreementFloatingRate  ***********************/
	class CollateralAgreementFloatingRateBuilderImpl extends FloatingRateBase.FloatingRateBaseBuilderImpl  implements CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder {
	
		protected Boolean negativeInterest;
		protected Boolean compressibleSpread;
	
		public CollateralAgreementFloatingRateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("negativeInterest")
		public Boolean getNegativeInterest() {
			return negativeInterest;
		}
		
		@Override
		@RosettaAttribute("compressibleSpread")
		public Boolean getCompressibleSpread() {
			return compressibleSpread;
		}
		
	
		@Override
		@RosettaAttribute("rateOption")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setRateOption(ReferenceWithMetaFloatingRateOption rateOption) {
			this.rateOption = rateOption==null?null:rateOption.toBuilder();
			return this;
		}
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setRateOptionValue(FloatingRateOption rateOption) {
			this.getOrCreateRateOption().setValue(rateOption);
			return this;
		}
		@Override
		@RosettaAttribute("spreadSchedule")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setSpreadSchedule(SpreadSchedule spreadSchedule) {
			this.spreadSchedule = spreadSchedule==null?null:spreadSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("capRateSchedule")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setCapRateSchedule(StrikeSchedule capRateSchedule) {
			this.capRateSchedule = capRateSchedule==null?null:capRateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floorRateSchedule")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setFloorRateSchedule(StrikeSchedule floorRateSchedule) {
			this.floorRateSchedule = floorRateSchedule==null?null:floorRateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("negativeInterest")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setNegativeInterest(Boolean negativeInterest) {
			this.negativeInterest = negativeInterest==null?null:negativeInterest;
			return this;
		}
		@Override
		@RosettaAttribute("compressibleSpread")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder setCompressibleSpread(Boolean compressibleSpread) {
			this.compressibleSpread = compressibleSpread==null?null:compressibleSpread;
			return this;
		}
		
		@Override
		public CollateralAgreementFloatingRate build() {
			return new CollateralAgreementFloatingRate.CollateralAgreementFloatingRateImpl(this);
		}
		
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getNegativeInterest()!=null) return true;
			if (getCompressibleSpread()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder o = (CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder) other;
			
			
			merger.mergeBasic(getNegativeInterest(), o.getNegativeInterest(), this::setNegativeInterest);
			merger.mergeBasic(getCompressibleSpread(), o.getCompressibleSpread(), this::setCompressibleSpread);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CollateralAgreementFloatingRate _that = getType().cast(o);
		
			if (!Objects.equals(negativeInterest, _that.getNegativeInterest())) return false;
			if (!Objects.equals(compressibleSpread, _that.getCompressibleSpread())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (negativeInterest != null ? negativeInterest.hashCode() : 0);
			_result = 31 * _result + (compressibleSpread != null ? compressibleSpread.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralAgreementFloatingRateBuilder {" +
				"negativeInterest=" + this.negativeInterest + ", " +
				"compressibleSpread=" + this.compressibleSpread +
			'}' + " " + super.toString();
		}
	}
}
