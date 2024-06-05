package cdm.product.collateral;

import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.CollateralProvisions.CollateralProvisionsBuilder;
import cdm.product.collateral.CollateralProvisions.CollateralProvisionsBuilderImpl;
import cdm.product.collateral.CollateralProvisions.CollateralProvisionsImpl;
import cdm.product.collateral.CollateralTypeEnum;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.SubstitutionProvisions;
import cdm.product.collateral.meta.CollateralProvisionsMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Contains collateral attributes which can also inherit information from a GMRA
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralProvisions", builder=CollateralProvisions.CollateralProvisionsBuilderImpl.class, version="${project.version}")
public interface CollateralProvisions extends RosettaModelObject {

	CollateralProvisionsMeta metaData = new CollateralProvisionsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Enumerates the collateral types which are accepted by the Seller.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "marginType"
	 *
	 * Provision 
	 *
	 */
	CollateralTypeEnum getCollateralType();
	/**
	 * The eligible collateral as specified in relation to the transaction.
	 */
	List<? extends EligibleCollateralCriteria> getEligibleCollateral();
	/**
	 * The provisions for collateral substitutions such as how many and when they are allowed.
	 */
	SubstitutionProvisions getSubstitutionProvisions();

	/*********************** Build Methods  ***********************/
	CollateralProvisions build();
	
	CollateralProvisions.CollateralProvisionsBuilder toBuilder();
	
	static CollateralProvisions.CollateralProvisionsBuilder builder() {
		return new CollateralProvisions.CollateralProvisionsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralProvisions> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralProvisions> getType() {
		return CollateralProvisions.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("collateralType"), CollateralTypeEnum.class, getCollateralType(), this);
		processRosetta(path.newSubPath("eligibleCollateral"), processor, EligibleCollateralCriteria.class, getEligibleCollateral());
		processRosetta(path.newSubPath("substitutionProvisions"), processor, SubstitutionProvisions.class, getSubstitutionProvisions());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralProvisionsBuilder extends CollateralProvisions, RosettaModelObjectBuilder {
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateEligibleCollateral(int _index);
		List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getEligibleCollateral();
		SubstitutionProvisions.SubstitutionProvisionsBuilder getOrCreateSubstitutionProvisions();
		SubstitutionProvisions.SubstitutionProvisionsBuilder getSubstitutionProvisions();
		CollateralProvisions.CollateralProvisionsBuilder setCollateralType(CollateralTypeEnum collateralType);
		CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(EligibleCollateralCriteria eligibleCollateral0);
		CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(EligibleCollateralCriteria eligibleCollateral1, int _idx);
		CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(List<? extends EligibleCollateralCriteria> eligibleCollateral2);
		CollateralProvisions.CollateralProvisionsBuilder setEligibleCollateral(List<? extends EligibleCollateralCriteria> eligibleCollateral3);
		CollateralProvisions.CollateralProvisionsBuilder setSubstitutionProvisions(SubstitutionProvisions substitutionProvisions);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("collateralType"), CollateralTypeEnum.class, getCollateralType(), this);
			processRosetta(path.newSubPath("eligibleCollateral"), processor, EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder.class, getEligibleCollateral());
			processRosetta(path.newSubPath("substitutionProvisions"), processor, SubstitutionProvisions.SubstitutionProvisionsBuilder.class, getSubstitutionProvisions());
		}
		

		CollateralProvisions.CollateralProvisionsBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralProvisions  ***********************/
	class CollateralProvisionsImpl implements CollateralProvisions {
		private final CollateralTypeEnum collateralType;
		private final List<? extends EligibleCollateralCriteria> eligibleCollateral;
		private final SubstitutionProvisions substitutionProvisions;
		
		protected CollateralProvisionsImpl(CollateralProvisions.CollateralProvisionsBuilder builder) {
			this.collateralType = builder.getCollateralType();
			this.eligibleCollateral = ofNullable(builder.getEligibleCollateral()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.substitutionProvisions = ofNullable(builder.getSubstitutionProvisions()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("collateralType")
		public CollateralTypeEnum getCollateralType() {
			return collateralType;
		}
		
		@Override
		@RosettaAttribute("eligibleCollateral")
		public List<? extends EligibleCollateralCriteria> getEligibleCollateral() {
			return eligibleCollateral;
		}
		
		@Override
		@RosettaAttribute("substitutionProvisions")
		public SubstitutionProvisions getSubstitutionProvisions() {
			return substitutionProvisions;
		}
		
		@Override
		public CollateralProvisions build() {
			return this;
		}
		
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder toBuilder() {
			CollateralProvisions.CollateralProvisionsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralProvisions.CollateralProvisionsBuilder builder) {
			ofNullable(getCollateralType()).ifPresent(builder::setCollateralType);
			ofNullable(getEligibleCollateral()).ifPresent(builder::setEligibleCollateral);
			ofNullable(getSubstitutionProvisions()).ifPresent(builder::setSubstitutionProvisions);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralProvisions _that = getType().cast(o);
		
			if (!Objects.equals(collateralType, _that.getCollateralType())) return false;
			if (!ListEquals.listEquals(eligibleCollateral, _that.getEligibleCollateral())) return false;
			if (!Objects.equals(substitutionProvisions, _that.getSubstitutionProvisions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralType != null ? collateralType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (eligibleCollateral != null ? eligibleCollateral.hashCode() : 0);
			_result = 31 * _result + (substitutionProvisions != null ? substitutionProvisions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralProvisions {" +
				"collateralType=" + this.collateralType + ", " +
				"eligibleCollateral=" + this.eligibleCollateral + ", " +
				"substitutionProvisions=" + this.substitutionProvisions +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralProvisions  ***********************/
	class CollateralProvisionsBuilderImpl implements CollateralProvisions.CollateralProvisionsBuilder {
	
		protected CollateralTypeEnum collateralType;
		protected List<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> eligibleCollateral = new ArrayList<>();
		protected SubstitutionProvisions.SubstitutionProvisionsBuilder substitutionProvisions;
	
		public CollateralProvisionsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("collateralType")
		public CollateralTypeEnum getCollateralType() {
			return collateralType;
		}
		
		@Override
		@RosettaAttribute("eligibleCollateral")
		public List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getEligibleCollateral() {
			return eligibleCollateral;
		}
		
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateEligibleCollateral(int _index) {
		
			if (eligibleCollateral==null) {
				this.eligibleCollateral = new ArrayList<>();
			}
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder result;
			return getIndex(eligibleCollateral, _index, () -> {
						EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder newEligibleCollateral = EligibleCollateralCriteria.builder();
						return newEligibleCollateral;
					});
		}
		
		@Override
		@RosettaAttribute("substitutionProvisions")
		public SubstitutionProvisions.SubstitutionProvisionsBuilder getSubstitutionProvisions() {
			return substitutionProvisions;
		}
		
		@Override
		public SubstitutionProvisions.SubstitutionProvisionsBuilder getOrCreateSubstitutionProvisions() {
			SubstitutionProvisions.SubstitutionProvisionsBuilder result;
			if (substitutionProvisions!=null) {
				result = substitutionProvisions;
			}
			else {
				result = substitutionProvisions = SubstitutionProvisions.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("collateralType")
		public CollateralProvisions.CollateralProvisionsBuilder setCollateralType(CollateralTypeEnum collateralType) {
			this.collateralType = collateralType==null?null:collateralType;
			return this;
		}
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(EligibleCollateralCriteria eligibleCollateral) {
			if (eligibleCollateral!=null) this.eligibleCollateral.add(eligibleCollateral.toBuilder());
			return this;
		}
		
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(EligibleCollateralCriteria eligibleCollateral, int _idx) {
			getIndex(this.eligibleCollateral, _idx, () -> eligibleCollateral.toBuilder());
			return this;
		}
		@Override 
		public CollateralProvisions.CollateralProvisionsBuilder addEligibleCollateral(List<? extends EligibleCollateralCriteria> eligibleCollaterals) {
			if (eligibleCollaterals != null) {
				for (EligibleCollateralCriteria toAdd : eligibleCollaterals) {
					this.eligibleCollateral.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("eligibleCollateral")
		public CollateralProvisions.CollateralProvisionsBuilder setEligibleCollateral(List<? extends EligibleCollateralCriteria> eligibleCollaterals) {
			if (eligibleCollaterals == null)  {
				this.eligibleCollateral = new ArrayList<>();
			}
			else {
				this.eligibleCollateral = eligibleCollaterals.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("substitutionProvisions")
		public CollateralProvisions.CollateralProvisionsBuilder setSubstitutionProvisions(SubstitutionProvisions substitutionProvisions) {
			this.substitutionProvisions = substitutionProvisions==null?null:substitutionProvisions.toBuilder();
			return this;
		}
		
		@Override
		public CollateralProvisions build() {
			return new CollateralProvisions.CollateralProvisionsImpl(this);
		}
		
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder prune() {
			eligibleCollateral = eligibleCollateral.stream().filter(b->b!=null).<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (substitutionProvisions!=null && !substitutionProvisions.prune().hasData()) substitutionProvisions = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCollateralType()!=null) return true;
			if (getEligibleCollateral()!=null && getEligibleCollateral().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getSubstitutionProvisions()!=null && getSubstitutionProvisions().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralProvisions.CollateralProvisionsBuilder o = (CollateralProvisions.CollateralProvisionsBuilder) other;
			
			merger.mergeRosetta(getEligibleCollateral(), o.getEligibleCollateral(), this::getOrCreateEligibleCollateral);
			merger.mergeRosetta(getSubstitutionProvisions(), o.getSubstitutionProvisions(), this::setSubstitutionProvisions);
			
			merger.mergeBasic(getCollateralType(), o.getCollateralType(), this::setCollateralType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralProvisions _that = getType().cast(o);
		
			if (!Objects.equals(collateralType, _that.getCollateralType())) return false;
			if (!ListEquals.listEquals(eligibleCollateral, _that.getEligibleCollateral())) return false;
			if (!Objects.equals(substitutionProvisions, _that.getSubstitutionProvisions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralType != null ? collateralType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (eligibleCollateral != null ? eligibleCollateral.hashCode() : 0);
			_result = 31 * _result + (substitutionProvisions != null ? substitutionProvisions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralProvisionsBuilder {" +
				"collateralType=" + this.collateralType + ", " +
				"eligibleCollateral=" + this.eligibleCollateral + ", " +
				"substitutionProvisions=" + this.substitutionProvisions +
			'}';
		}
	}
}
