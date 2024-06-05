package cdm.product.asset;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder;
import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.DiscountingMethod.DiscountingMethodBuilder;
import cdm.product.asset.DiscountingMethod.DiscountingMethodBuilderImpl;
import cdm.product.asset.DiscountingMethod.DiscountingMethodImpl;
import cdm.product.asset.DiscountingTypeEnum;
import cdm.product.asset.meta.DiscountingMethodMeta;
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
 * A data defining:  discounting information. The 2000 ISDA definitions, section 8.4. discounting (related to the calculation of a discounted fixed amount or floating amount) apply. This type must only be included if discounting applies.
 * @version ${project.version}
 */
@RosettaDataType(value="DiscountingMethod", builder=DiscountingMethod.DiscountingMethodBuilderImpl.class, version="${project.version}")
public interface DiscountingMethod extends RosettaModelObject {

	DiscountingMethodMeta metaData = new DiscountingMethodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The discounting method that is applicable.
	 */
	DiscountingTypeEnum getDiscountingType();
	/**
	 * A discount rate, expressed as a decimal, to be used in the calculation of a discounted amount. A discount amount of 5% would be represented as 0.05.
	 */
	BigDecimal getDiscountRate();
	/**
	 * A discount day count fraction to be used in the calculation of a discounted amount.
	 */
	FieldWithMetaDayCountFractionEnum getDiscountRateDayCountFraction();

	/*********************** Build Methods  ***********************/
	DiscountingMethod build();
	
	DiscountingMethod.DiscountingMethodBuilder toBuilder();
	
	static DiscountingMethod.DiscountingMethodBuilder builder() {
		return new DiscountingMethod.DiscountingMethodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DiscountingMethod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DiscountingMethod> getType() {
		return DiscountingMethod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("discountingType"), DiscountingTypeEnum.class, getDiscountingType(), this);
		processor.processBasic(path.newSubPath("discountRate"), BigDecimal.class, getDiscountRate(), this);
		processRosetta(path.newSubPath("discountRateDayCountFraction"), processor, FieldWithMetaDayCountFractionEnum.class, getDiscountRateDayCountFraction());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DiscountingMethodBuilder extends DiscountingMethod, RosettaModelObjectBuilder {
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getOrCreateDiscountRateDayCountFraction();
		FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getDiscountRateDayCountFraction();
		DiscountingMethod.DiscountingMethodBuilder setDiscountingType(DiscountingTypeEnum discountingType);
		DiscountingMethod.DiscountingMethodBuilder setDiscountRate(BigDecimal discountRate);
		DiscountingMethod.DiscountingMethodBuilder setDiscountRateDayCountFraction(FieldWithMetaDayCountFractionEnum discountRateDayCountFraction0);
		DiscountingMethod.DiscountingMethodBuilder setDiscountRateDayCountFractionValue(DayCountFractionEnum discountRateDayCountFraction1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("discountingType"), DiscountingTypeEnum.class, getDiscountingType(), this);
			processor.processBasic(path.newSubPath("discountRate"), BigDecimal.class, getDiscountRate(), this);
			processRosetta(path.newSubPath("discountRateDayCountFraction"), processor, FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder.class, getDiscountRateDayCountFraction());
		}
		

		DiscountingMethod.DiscountingMethodBuilder prune();
	}

	/*********************** Immutable Implementation of DiscountingMethod  ***********************/
	class DiscountingMethodImpl implements DiscountingMethod {
		private final DiscountingTypeEnum discountingType;
		private final BigDecimal discountRate;
		private final FieldWithMetaDayCountFractionEnum discountRateDayCountFraction;
		
		protected DiscountingMethodImpl(DiscountingMethod.DiscountingMethodBuilder builder) {
			this.discountingType = builder.getDiscountingType();
			this.discountRate = builder.getDiscountRate();
			this.discountRateDayCountFraction = ofNullable(builder.getDiscountRateDayCountFraction()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("discountingType")
		public DiscountingTypeEnum getDiscountingType() {
			return discountingType;
		}
		
		@Override
		@RosettaAttribute("discountRate")
		public BigDecimal getDiscountRate() {
			return discountRate;
		}
		
		@Override
		@RosettaAttribute("discountRateDayCountFraction")
		public FieldWithMetaDayCountFractionEnum getDiscountRateDayCountFraction() {
			return discountRateDayCountFraction;
		}
		
		@Override
		public DiscountingMethod build() {
			return this;
		}
		
		@Override
		public DiscountingMethod.DiscountingMethodBuilder toBuilder() {
			DiscountingMethod.DiscountingMethodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DiscountingMethod.DiscountingMethodBuilder builder) {
			ofNullable(getDiscountingType()).ifPresent(builder::setDiscountingType);
			ofNullable(getDiscountRate()).ifPresent(builder::setDiscountRate);
			ofNullable(getDiscountRateDayCountFraction()).ifPresent(builder::setDiscountRateDayCountFraction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DiscountingMethod _that = getType().cast(o);
		
			if (!Objects.equals(discountingType, _that.getDiscountingType())) return false;
			if (!Objects.equals(discountRate, _that.getDiscountRate())) return false;
			if (!Objects.equals(discountRateDayCountFraction, _that.getDiscountRateDayCountFraction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (discountingType != null ? discountingType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (discountRate != null ? discountRate.hashCode() : 0);
			_result = 31 * _result + (discountRateDayCountFraction != null ? discountRateDayCountFraction.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DiscountingMethod {" +
				"discountingType=" + this.discountingType + ", " +
				"discountRate=" + this.discountRate + ", " +
				"discountRateDayCountFraction=" + this.discountRateDayCountFraction +
			'}';
		}
	}

	/*********************** Builder Implementation of DiscountingMethod  ***********************/
	class DiscountingMethodBuilderImpl implements DiscountingMethod.DiscountingMethodBuilder {
	
		protected DiscountingTypeEnum discountingType;
		protected BigDecimal discountRate;
		protected FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder discountRateDayCountFraction;
	
		public DiscountingMethodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("discountingType")
		public DiscountingTypeEnum getDiscountingType() {
			return discountingType;
		}
		
		@Override
		@RosettaAttribute("discountRate")
		public BigDecimal getDiscountRate() {
			return discountRate;
		}
		
		@Override
		@RosettaAttribute("discountRateDayCountFraction")
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getDiscountRateDayCountFraction() {
			return discountRateDayCountFraction;
		}
		
		@Override
		public FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder getOrCreateDiscountRateDayCountFraction() {
			FieldWithMetaDayCountFractionEnum.FieldWithMetaDayCountFractionEnumBuilder result;
			if (discountRateDayCountFraction!=null) {
				result = discountRateDayCountFraction;
			}
			else {
				result = discountRateDayCountFraction = FieldWithMetaDayCountFractionEnum.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("discountingType")
		public DiscountingMethod.DiscountingMethodBuilder setDiscountingType(DiscountingTypeEnum discountingType) {
			this.discountingType = discountingType==null?null:discountingType;
			return this;
		}
		@Override
		@RosettaAttribute("discountRate")
		public DiscountingMethod.DiscountingMethodBuilder setDiscountRate(BigDecimal discountRate) {
			this.discountRate = discountRate==null?null:discountRate;
			return this;
		}
		@Override
		@RosettaAttribute("discountRateDayCountFraction")
		public DiscountingMethod.DiscountingMethodBuilder setDiscountRateDayCountFraction(FieldWithMetaDayCountFractionEnum discountRateDayCountFraction) {
			this.discountRateDayCountFraction = discountRateDayCountFraction==null?null:discountRateDayCountFraction.toBuilder();
			return this;
		}
		@Override
		public DiscountingMethod.DiscountingMethodBuilder setDiscountRateDayCountFractionValue(DayCountFractionEnum discountRateDayCountFraction) {
			this.getOrCreateDiscountRateDayCountFraction().setValue(discountRateDayCountFraction);
			return this;
		}
		
		@Override
		public DiscountingMethod build() {
			return new DiscountingMethod.DiscountingMethodImpl(this);
		}
		
		@Override
		public DiscountingMethod.DiscountingMethodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DiscountingMethod.DiscountingMethodBuilder prune() {
			if (discountRateDayCountFraction!=null && !discountRateDayCountFraction.prune().hasData()) discountRateDayCountFraction = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDiscountingType()!=null) return true;
			if (getDiscountRate()!=null) return true;
			if (getDiscountRateDayCountFraction()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DiscountingMethod.DiscountingMethodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DiscountingMethod.DiscountingMethodBuilder o = (DiscountingMethod.DiscountingMethodBuilder) other;
			
			merger.mergeRosetta(getDiscountRateDayCountFraction(), o.getDiscountRateDayCountFraction(), this::setDiscountRateDayCountFraction);
			
			merger.mergeBasic(getDiscountingType(), o.getDiscountingType(), this::setDiscountingType);
			merger.mergeBasic(getDiscountRate(), o.getDiscountRate(), this::setDiscountRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DiscountingMethod _that = getType().cast(o);
		
			if (!Objects.equals(discountingType, _that.getDiscountingType())) return false;
			if (!Objects.equals(discountRate, _that.getDiscountRate())) return false;
			if (!Objects.equals(discountRateDayCountFraction, _that.getDiscountRateDayCountFraction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (discountingType != null ? discountingType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (discountRate != null ? discountRate.hashCode() : 0);
			_result = 31 * _result + (discountRateDayCountFraction != null ? discountRateDayCountFraction.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DiscountingMethodBuilder {" +
				"discountingType=" + this.discountingType + ", " +
				"discountRate=" + this.discountRate + ", " +
				"discountRateDayCountFraction=" + this.discountRateDayCountFraction +
			'}';
		}
	}
}
