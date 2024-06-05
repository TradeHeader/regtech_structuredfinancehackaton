package cdm.product.asset;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.product.asset.BondReference;
import cdm.product.asset.BondReference.BondReferenceBuilder;
import cdm.product.asset.BondReference.BondReferenceBuilderImpl;
import cdm.product.asset.BondReference.BondReferenceImpl;
import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.meta.BondReferenceMeta;
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
 * Reference to a bond underlier to represent an asset swap or Condition Precedent Bond.
 * @version ${project.version}
 */
@RosettaDataType(value="BondReference", builder=BondReference.BondReferenceBuilderImpl.class, version="${project.version}")
public interface BondReference extends RosettaModelObject {

	BondReferenceMeta metaData = new BondReferenceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Reference to a bond underlier.
	 */
	ProductIdentifier getBond();
	/**
	 * To indicate whether the Condition Precedent Bond is applicable. The swap contract is only valid if the bond is issued and if there is any dispute over the terms of fixed stream then the bond terms would be used.
	 */
	Boolean getConditionPrecedentBond();
	/**
	 * To indicate whether the Discrepancy Clause is applicable.
	 */
	Boolean getDiscrepancyClause();
	/**
	 * Specifies the coupon rate (expressed in percentage) of a fixed income security or convertible bond.
	 */
	FixedRateSpecification getCouponRate();

	/*********************** Build Methods  ***********************/
	BondReference build();
	
	BondReference.BondReferenceBuilder toBuilder();
	
	static BondReference.BondReferenceBuilder builder() {
		return new BondReference.BondReferenceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BondReference> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BondReference> getType() {
		return BondReference.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("bond"), processor, ProductIdentifier.class, getBond());
		processor.processBasic(path.newSubPath("conditionPrecedentBond"), Boolean.class, getConditionPrecedentBond(), this);
		processor.processBasic(path.newSubPath("discrepancyClause"), Boolean.class, getDiscrepancyClause(), this);
		processRosetta(path.newSubPath("couponRate"), processor, FixedRateSpecification.class, getCouponRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BondReferenceBuilder extends BondReference, RosettaModelObjectBuilder {
		ProductIdentifier.ProductIdentifierBuilder getOrCreateBond();
		ProductIdentifier.ProductIdentifierBuilder getBond();
		FixedRateSpecification.FixedRateSpecificationBuilder getOrCreateCouponRate();
		FixedRateSpecification.FixedRateSpecificationBuilder getCouponRate();
		BondReference.BondReferenceBuilder setBond(ProductIdentifier bond);
		BondReference.BondReferenceBuilder setConditionPrecedentBond(Boolean conditionPrecedentBond);
		BondReference.BondReferenceBuilder setDiscrepancyClause(Boolean discrepancyClause);
		BondReference.BondReferenceBuilder setCouponRate(FixedRateSpecification couponRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("bond"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getBond());
			processor.processBasic(path.newSubPath("conditionPrecedentBond"), Boolean.class, getConditionPrecedentBond(), this);
			processor.processBasic(path.newSubPath("discrepancyClause"), Boolean.class, getDiscrepancyClause(), this);
			processRosetta(path.newSubPath("couponRate"), processor, FixedRateSpecification.FixedRateSpecificationBuilder.class, getCouponRate());
		}
		

		BondReference.BondReferenceBuilder prune();
	}

	/*********************** Immutable Implementation of BondReference  ***********************/
	class BondReferenceImpl implements BondReference {
		private final ProductIdentifier bond;
		private final Boolean conditionPrecedentBond;
		private final Boolean discrepancyClause;
		private final FixedRateSpecification couponRate;
		
		protected BondReferenceImpl(BondReference.BondReferenceBuilder builder) {
			this.bond = ofNullable(builder.getBond()).map(f->f.build()).orElse(null);
			this.conditionPrecedentBond = builder.getConditionPrecedentBond();
			this.discrepancyClause = builder.getDiscrepancyClause();
			this.couponRate = ofNullable(builder.getCouponRate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("bond")
		public ProductIdentifier getBond() {
			return bond;
		}
		
		@Override
		@RosettaAttribute("conditionPrecedentBond")
		public Boolean getConditionPrecedentBond() {
			return conditionPrecedentBond;
		}
		
		@Override
		@RosettaAttribute("discrepancyClause")
		public Boolean getDiscrepancyClause() {
			return discrepancyClause;
		}
		
		@Override
		@RosettaAttribute("couponRate")
		public FixedRateSpecification getCouponRate() {
			return couponRate;
		}
		
		@Override
		public BondReference build() {
			return this;
		}
		
		@Override
		public BondReference.BondReferenceBuilder toBuilder() {
			BondReference.BondReferenceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BondReference.BondReferenceBuilder builder) {
			ofNullable(getBond()).ifPresent(builder::setBond);
			ofNullable(getConditionPrecedentBond()).ifPresent(builder::setConditionPrecedentBond);
			ofNullable(getDiscrepancyClause()).ifPresent(builder::setDiscrepancyClause);
			ofNullable(getCouponRate()).ifPresent(builder::setCouponRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondReference _that = getType().cast(o);
		
			if (!Objects.equals(bond, _that.getBond())) return false;
			if (!Objects.equals(conditionPrecedentBond, _that.getConditionPrecedentBond())) return false;
			if (!Objects.equals(discrepancyClause, _that.getDiscrepancyClause())) return false;
			if (!Objects.equals(couponRate, _that.getCouponRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bond != null ? bond.hashCode() : 0);
			_result = 31 * _result + (conditionPrecedentBond != null ? conditionPrecedentBond.hashCode() : 0);
			_result = 31 * _result + (discrepancyClause != null ? discrepancyClause.hashCode() : 0);
			_result = 31 * _result + (couponRate != null ? couponRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondReference {" +
				"bond=" + this.bond + ", " +
				"conditionPrecedentBond=" + this.conditionPrecedentBond + ", " +
				"discrepancyClause=" + this.discrepancyClause + ", " +
				"couponRate=" + this.couponRate +
			'}';
		}
	}

	/*********************** Builder Implementation of BondReference  ***********************/
	class BondReferenceBuilderImpl implements BondReference.BondReferenceBuilder {
	
		protected ProductIdentifier.ProductIdentifierBuilder bond;
		protected Boolean conditionPrecedentBond;
		protected Boolean discrepancyClause;
		protected FixedRateSpecification.FixedRateSpecificationBuilder couponRate;
	
		public BondReferenceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("bond")
		public ProductIdentifier.ProductIdentifierBuilder getBond() {
			return bond;
		}
		
		@Override
		public ProductIdentifier.ProductIdentifierBuilder getOrCreateBond() {
			ProductIdentifier.ProductIdentifierBuilder result;
			if (bond!=null) {
				result = bond;
			}
			else {
				result = bond = ProductIdentifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("conditionPrecedentBond")
		public Boolean getConditionPrecedentBond() {
			return conditionPrecedentBond;
		}
		
		@Override
		@RosettaAttribute("discrepancyClause")
		public Boolean getDiscrepancyClause() {
			return discrepancyClause;
		}
		
		@Override
		@RosettaAttribute("couponRate")
		public FixedRateSpecification.FixedRateSpecificationBuilder getCouponRate() {
			return couponRate;
		}
		
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder getOrCreateCouponRate() {
			FixedRateSpecification.FixedRateSpecificationBuilder result;
			if (couponRate!=null) {
				result = couponRate;
			}
			else {
				result = couponRate = FixedRateSpecification.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("bond")
		public BondReference.BondReferenceBuilder setBond(ProductIdentifier bond) {
			this.bond = bond==null?null:bond.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("conditionPrecedentBond")
		public BondReference.BondReferenceBuilder setConditionPrecedentBond(Boolean conditionPrecedentBond) {
			this.conditionPrecedentBond = conditionPrecedentBond==null?null:conditionPrecedentBond;
			return this;
		}
		@Override
		@RosettaAttribute("discrepancyClause")
		public BondReference.BondReferenceBuilder setDiscrepancyClause(Boolean discrepancyClause) {
			this.discrepancyClause = discrepancyClause==null?null:discrepancyClause;
			return this;
		}
		@Override
		@RosettaAttribute("couponRate")
		public BondReference.BondReferenceBuilder setCouponRate(FixedRateSpecification couponRate) {
			this.couponRate = couponRate==null?null:couponRate.toBuilder();
			return this;
		}
		
		@Override
		public BondReference build() {
			return new BondReference.BondReferenceImpl(this);
		}
		
		@Override
		public BondReference.BondReferenceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondReference.BondReferenceBuilder prune() {
			if (bond!=null && !bond.prune().hasData()) bond = null;
			if (couponRate!=null && !couponRate.prune().hasData()) couponRate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBond()!=null && getBond().hasData()) return true;
			if (getConditionPrecedentBond()!=null) return true;
			if (getDiscrepancyClause()!=null) return true;
			if (getCouponRate()!=null && getCouponRate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondReference.BondReferenceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BondReference.BondReferenceBuilder o = (BondReference.BondReferenceBuilder) other;
			
			merger.mergeRosetta(getBond(), o.getBond(), this::setBond);
			merger.mergeRosetta(getCouponRate(), o.getCouponRate(), this::setCouponRate);
			
			merger.mergeBasic(getConditionPrecedentBond(), o.getConditionPrecedentBond(), this::setConditionPrecedentBond);
			merger.mergeBasic(getDiscrepancyClause(), o.getDiscrepancyClause(), this::setDiscrepancyClause);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondReference _that = getType().cast(o);
		
			if (!Objects.equals(bond, _that.getBond())) return false;
			if (!Objects.equals(conditionPrecedentBond, _that.getConditionPrecedentBond())) return false;
			if (!Objects.equals(discrepancyClause, _that.getDiscrepancyClause())) return false;
			if (!Objects.equals(couponRate, _that.getCouponRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bond != null ? bond.hashCode() : 0);
			_result = 31 * _result + (conditionPrecedentBond != null ? conditionPrecedentBond.hashCode() : 0);
			_result = 31 * _result + (discrepancyClause != null ? discrepancyClause.hashCode() : 0);
			_result = 31 * _result + (couponRate != null ? couponRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondReferenceBuilder {" +
				"bond=" + this.bond + ", " +
				"conditionPrecedentBond=" + this.conditionPrecedentBond + ", " +
				"discrepancyClause=" + this.discrepancyClause + ", " +
				"couponRate=" + this.couponRate +
			'}';
		}
	}
}
