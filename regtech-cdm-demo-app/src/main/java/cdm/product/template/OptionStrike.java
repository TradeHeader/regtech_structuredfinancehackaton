package cdm.product.template;

import cdm.observable.asset.Price;
import cdm.observable.asset.ReferenceSwapCurve;
import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.metafields.ReferenceWithMetaFixedRateSpecification;
import cdm.product.asset.metafields.ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder;
import cdm.product.template.AveragingStrikeFeature;
import cdm.product.template.OptionStrike;
import cdm.product.template.OptionStrike.OptionStrikeBuilder;
import cdm.product.template.OptionStrike.OptionStrikeBuilderImpl;
import cdm.product.template.OptionStrike.OptionStrikeImpl;
import cdm.product.template.meta.OptionStrikeMeta;
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
 * Defines the strike price of an option.
 * @version ${project.version}
 */
@RosettaDataType(value="OptionStrike", builder=OptionStrike.OptionStrikeBuilderImpl.class, version="${project.version}")
public interface OptionStrike extends RosettaModelObject {

	OptionStrikeMeta metaData = new OptionStrikeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the strike of an option in the form of a price that could be a cash price, interestRate, or other types.
	 */
	Price getStrikePrice();
	/**
	 * Defines the strike of an option in reference to the spread of the underlying swap (typical practice in the case of an option on a credit single name swaps).
	 */
	ReferenceWithMetaFixedRateSpecification getStrikeReference();
	/**
	 * Defines the strike of an option when expressed by reference to a swap curve (Typically the case for a convertible bond option).
	 */
	ReferenceSwapCurve getReferenceSwapCurve();
	/**
	 * Defines an  option strike that is calculated from an average of observed market prices.
	 */
	AveragingStrikeFeature getAveragingStrikeFeature();

	/*********************** Build Methods  ***********************/
	OptionStrike build();
	
	OptionStrike.OptionStrikeBuilder toBuilder();
	
	static OptionStrike.OptionStrikeBuilder builder() {
		return new OptionStrike.OptionStrikeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OptionStrike> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OptionStrike> getType() {
		return OptionStrike.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("strikePrice"), processor, Price.class, getStrikePrice());
		processRosetta(path.newSubPath("strikeReference"), processor, ReferenceWithMetaFixedRateSpecification.class, getStrikeReference());
		processRosetta(path.newSubPath("referenceSwapCurve"), processor, ReferenceSwapCurve.class, getReferenceSwapCurve());
		processRosetta(path.newSubPath("averagingStrikeFeature"), processor, AveragingStrikeFeature.class, getAveragingStrikeFeature());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OptionStrikeBuilder extends OptionStrike, RosettaModelObjectBuilder {
		Price.PriceBuilder getOrCreateStrikePrice();
		Price.PriceBuilder getStrikePrice();
		ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder getOrCreateStrikeReference();
		ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder getStrikeReference();
		ReferenceSwapCurve.ReferenceSwapCurveBuilder getOrCreateReferenceSwapCurve();
		ReferenceSwapCurve.ReferenceSwapCurveBuilder getReferenceSwapCurve();
		AveragingStrikeFeature.AveragingStrikeFeatureBuilder getOrCreateAveragingStrikeFeature();
		AveragingStrikeFeature.AveragingStrikeFeatureBuilder getAveragingStrikeFeature();
		OptionStrike.OptionStrikeBuilder setStrikePrice(Price strikePrice);
		OptionStrike.OptionStrikeBuilder setStrikeReference(ReferenceWithMetaFixedRateSpecification strikeReference0);
		OptionStrike.OptionStrikeBuilder setStrikeReferenceValue(FixedRateSpecification strikeReference1);
		OptionStrike.OptionStrikeBuilder setReferenceSwapCurve(ReferenceSwapCurve referenceSwapCurve);
		OptionStrike.OptionStrikeBuilder setAveragingStrikeFeature(AveragingStrikeFeature averagingStrikeFeature);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("strikePrice"), processor, Price.PriceBuilder.class, getStrikePrice());
			processRosetta(path.newSubPath("strikeReference"), processor, ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder.class, getStrikeReference());
			processRosetta(path.newSubPath("referenceSwapCurve"), processor, ReferenceSwapCurve.ReferenceSwapCurveBuilder.class, getReferenceSwapCurve());
			processRosetta(path.newSubPath("averagingStrikeFeature"), processor, AveragingStrikeFeature.AveragingStrikeFeatureBuilder.class, getAveragingStrikeFeature());
		}
		

		OptionStrike.OptionStrikeBuilder prune();
	}

	/*********************** Immutable Implementation of OptionStrike  ***********************/
	class OptionStrikeImpl implements OptionStrike {
		private final Price strikePrice;
		private final ReferenceWithMetaFixedRateSpecification strikeReference;
		private final ReferenceSwapCurve referenceSwapCurve;
		private final AveragingStrikeFeature averagingStrikeFeature;
		
		protected OptionStrikeImpl(OptionStrike.OptionStrikeBuilder builder) {
			this.strikePrice = ofNullable(builder.getStrikePrice()).map(f->f.build()).orElse(null);
			this.strikeReference = ofNullable(builder.getStrikeReference()).map(f->f.build()).orElse(null);
			this.referenceSwapCurve = ofNullable(builder.getReferenceSwapCurve()).map(f->f.build()).orElse(null);
			this.averagingStrikeFeature = ofNullable(builder.getAveragingStrikeFeature()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("strikePrice")
		public Price getStrikePrice() {
			return strikePrice;
		}
		
		@Override
		@RosettaAttribute("strikeReference")
		public ReferenceWithMetaFixedRateSpecification getStrikeReference() {
			return strikeReference;
		}
		
		@Override
		@RosettaAttribute("referenceSwapCurve")
		public ReferenceSwapCurve getReferenceSwapCurve() {
			return referenceSwapCurve;
		}
		
		@Override
		@RosettaAttribute("averagingStrikeFeature")
		public AveragingStrikeFeature getAveragingStrikeFeature() {
			return averagingStrikeFeature;
		}
		
		@Override
		public OptionStrike build() {
			return this;
		}
		
		@Override
		public OptionStrike.OptionStrikeBuilder toBuilder() {
			OptionStrike.OptionStrikeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OptionStrike.OptionStrikeBuilder builder) {
			ofNullable(getStrikePrice()).ifPresent(builder::setStrikePrice);
			ofNullable(getStrikeReference()).ifPresent(builder::setStrikeReference);
			ofNullable(getReferenceSwapCurve()).ifPresent(builder::setReferenceSwapCurve);
			ofNullable(getAveragingStrikeFeature()).ifPresent(builder::setAveragingStrikeFeature);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionStrike _that = getType().cast(o);
		
			if (!Objects.equals(strikePrice, _that.getStrikePrice())) return false;
			if (!Objects.equals(strikeReference, _that.getStrikeReference())) return false;
			if (!Objects.equals(referenceSwapCurve, _that.getReferenceSwapCurve())) return false;
			if (!Objects.equals(averagingStrikeFeature, _that.getAveragingStrikeFeature())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikePrice != null ? strikePrice.hashCode() : 0);
			_result = 31 * _result + (strikeReference != null ? strikeReference.hashCode() : 0);
			_result = 31 * _result + (referenceSwapCurve != null ? referenceSwapCurve.hashCode() : 0);
			_result = 31 * _result + (averagingStrikeFeature != null ? averagingStrikeFeature.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionStrike {" +
				"strikePrice=" + this.strikePrice + ", " +
				"strikeReference=" + this.strikeReference + ", " +
				"referenceSwapCurve=" + this.referenceSwapCurve + ", " +
				"averagingStrikeFeature=" + this.averagingStrikeFeature +
			'}';
		}
	}

	/*********************** Builder Implementation of OptionStrike  ***********************/
	class OptionStrikeBuilderImpl implements OptionStrike.OptionStrikeBuilder {
	
		protected Price.PriceBuilder strikePrice;
		protected ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder strikeReference;
		protected ReferenceSwapCurve.ReferenceSwapCurveBuilder referenceSwapCurve;
		protected AveragingStrikeFeature.AveragingStrikeFeatureBuilder averagingStrikeFeature;
	
		public OptionStrikeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("strikePrice")
		public Price.PriceBuilder getStrikePrice() {
			return strikePrice;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateStrikePrice() {
			Price.PriceBuilder result;
			if (strikePrice!=null) {
				result = strikePrice;
			}
			else {
				result = strikePrice = Price.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("strikeReference")
		public ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder getStrikeReference() {
			return strikeReference;
		}
		
		@Override
		public ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder getOrCreateStrikeReference() {
			ReferenceWithMetaFixedRateSpecification.ReferenceWithMetaFixedRateSpecificationBuilder result;
			if (strikeReference!=null) {
				result = strikeReference;
			}
			else {
				result = strikeReference = ReferenceWithMetaFixedRateSpecification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("referenceSwapCurve")
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder getReferenceSwapCurve() {
			return referenceSwapCurve;
		}
		
		@Override
		public ReferenceSwapCurve.ReferenceSwapCurveBuilder getOrCreateReferenceSwapCurve() {
			ReferenceSwapCurve.ReferenceSwapCurveBuilder result;
			if (referenceSwapCurve!=null) {
				result = referenceSwapCurve;
			}
			else {
				result = referenceSwapCurve = ReferenceSwapCurve.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("averagingStrikeFeature")
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder getAveragingStrikeFeature() {
			return averagingStrikeFeature;
		}
		
		@Override
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder getOrCreateAveragingStrikeFeature() {
			AveragingStrikeFeature.AveragingStrikeFeatureBuilder result;
			if (averagingStrikeFeature!=null) {
				result = averagingStrikeFeature;
			}
			else {
				result = averagingStrikeFeature = AveragingStrikeFeature.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("strikePrice")
		public OptionStrike.OptionStrikeBuilder setStrikePrice(Price strikePrice) {
			this.strikePrice = strikePrice==null?null:strikePrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("strikeReference")
		public OptionStrike.OptionStrikeBuilder setStrikeReference(ReferenceWithMetaFixedRateSpecification strikeReference) {
			this.strikeReference = strikeReference==null?null:strikeReference.toBuilder();
			return this;
		}
		@Override
		public OptionStrike.OptionStrikeBuilder setStrikeReferenceValue(FixedRateSpecification strikeReference) {
			this.getOrCreateStrikeReference().setValue(strikeReference);
			return this;
		}
		@Override
		@RosettaAttribute("referenceSwapCurve")
		public OptionStrike.OptionStrikeBuilder setReferenceSwapCurve(ReferenceSwapCurve referenceSwapCurve) {
			this.referenceSwapCurve = referenceSwapCurve==null?null:referenceSwapCurve.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingStrikeFeature")
		public OptionStrike.OptionStrikeBuilder setAveragingStrikeFeature(AveragingStrikeFeature averagingStrikeFeature) {
			this.averagingStrikeFeature = averagingStrikeFeature==null?null:averagingStrikeFeature.toBuilder();
			return this;
		}
		
		@Override
		public OptionStrike build() {
			return new OptionStrike.OptionStrikeImpl(this);
		}
		
		@Override
		public OptionStrike.OptionStrikeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionStrike.OptionStrikeBuilder prune() {
			if (strikePrice!=null && !strikePrice.prune().hasData()) strikePrice = null;
			if (strikeReference!=null && !strikeReference.prune().hasData()) strikeReference = null;
			if (referenceSwapCurve!=null && !referenceSwapCurve.prune().hasData()) referenceSwapCurve = null;
			if (averagingStrikeFeature!=null && !averagingStrikeFeature.prune().hasData()) averagingStrikeFeature = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStrikePrice()!=null && getStrikePrice().hasData()) return true;
			if (getStrikeReference()!=null && getStrikeReference().hasData()) return true;
			if (getReferenceSwapCurve()!=null && getReferenceSwapCurve().hasData()) return true;
			if (getAveragingStrikeFeature()!=null && getAveragingStrikeFeature().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionStrike.OptionStrikeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OptionStrike.OptionStrikeBuilder o = (OptionStrike.OptionStrikeBuilder) other;
			
			merger.mergeRosetta(getStrikePrice(), o.getStrikePrice(), this::setStrikePrice);
			merger.mergeRosetta(getStrikeReference(), o.getStrikeReference(), this::setStrikeReference);
			merger.mergeRosetta(getReferenceSwapCurve(), o.getReferenceSwapCurve(), this::setReferenceSwapCurve);
			merger.mergeRosetta(getAveragingStrikeFeature(), o.getAveragingStrikeFeature(), this::setAveragingStrikeFeature);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionStrike _that = getType().cast(o);
		
			if (!Objects.equals(strikePrice, _that.getStrikePrice())) return false;
			if (!Objects.equals(strikeReference, _that.getStrikeReference())) return false;
			if (!Objects.equals(referenceSwapCurve, _that.getReferenceSwapCurve())) return false;
			if (!Objects.equals(averagingStrikeFeature, _that.getAveragingStrikeFeature())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikePrice != null ? strikePrice.hashCode() : 0);
			_result = 31 * _result + (strikeReference != null ? strikeReference.hashCode() : 0);
			_result = 31 * _result + (referenceSwapCurve != null ? referenceSwapCurve.hashCode() : 0);
			_result = 31 * _result + (averagingStrikeFeature != null ? averagingStrikeFeature.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionStrikeBuilder {" +
				"strikePrice=" + this.strikePrice + ", " +
				"strikeReference=" + this.strikeReference + ", " +
				"referenceSwapCurve=" + this.referenceSwapCurve + ", " +
				"averagingStrikeFeature=" + this.averagingStrikeFeature +
			'}';
		}
	}
}
