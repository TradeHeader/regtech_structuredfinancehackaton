package cdm.product.collateral;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotationBoundaryEnum;
import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.AgencyRatingCriteria.AgencyRatingCriteriaBuilder;
import cdm.product.collateral.AgencyRatingCriteria.AgencyRatingCriteriaBuilderImpl;
import cdm.product.collateral.AgencyRatingCriteria.AgencyRatingCriteriaImpl;
import cdm.product.collateral.meta.AgencyRatingCriteriaMeta;
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
 * Represents a class to specify multiple credit notations alongside a conditional &#39;any&#39; or &#39;all&#39; qualifier.
 * @version ${project.version}
 */
@RosettaDataType(value="AgencyRatingCriteria", builder=AgencyRatingCriteria.AgencyRatingCriteriaBuilderImpl.class, version="${project.version}")
public interface AgencyRatingCriteria extends RosettaModelObject {

	AgencyRatingCriteriaMeta metaData = new AgencyRatingCriteriaMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether all or any agency ratings apply.
	 */
	QuantifierEnum getQualifier();
	/**
	 * Indicates the agency rating criteria specified for the asset or issuer.
	 */
	List<? extends CreditNotation> getCreditNotation();
	/**
	 * Indicator for options to be used if several agency ratings (&gt;1) are specified and its necessary to identify specific charateristics. i.e (lowest or highest).
	 */
	CreditNotationMismatchResolutionEnum getMismatchResolution();
	/**
	 * identifies the dominant reference agency if there is a missmatch and several reference agencies exsist.
	 */
	CreditRatingAgencyEnum getReferenceAgency();
	/**
	 * Indicates the boundary of a credit agency rating i.e minimum or maximum.
	 */
	CreditNotationBoundaryEnum getBoundary();

	/*********************** Build Methods  ***********************/
	AgencyRatingCriteria build();
	
	AgencyRatingCriteria.AgencyRatingCriteriaBuilder toBuilder();
	
	static AgencyRatingCriteria.AgencyRatingCriteriaBuilder builder() {
		return new AgencyRatingCriteria.AgencyRatingCriteriaBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AgencyRatingCriteria> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AgencyRatingCriteria> getType() {
		return AgencyRatingCriteria.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("qualifier"), QuantifierEnum.class, getQualifier(), this);
		processRosetta(path.newSubPath("creditNotation"), processor, CreditNotation.class, getCreditNotation());
		processor.processBasic(path.newSubPath("mismatchResolution"), CreditNotationMismatchResolutionEnum.class, getMismatchResolution(), this);
		processor.processBasic(path.newSubPath("referenceAgency"), CreditRatingAgencyEnum.class, getReferenceAgency(), this);
		processor.processBasic(path.newSubPath("boundary"), CreditNotationBoundaryEnum.class, getBoundary(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AgencyRatingCriteriaBuilder extends AgencyRatingCriteria, RosettaModelObjectBuilder {
		CreditNotation.CreditNotationBuilder getOrCreateCreditNotation(int _index);
		List<? extends CreditNotation.CreditNotationBuilder> getCreditNotation();
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder setQualifier(QuantifierEnum qualifier);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(CreditNotation creditNotation0);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(CreditNotation creditNotation1, int _idx);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(List<? extends CreditNotation> creditNotation2);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder setCreditNotation(List<? extends CreditNotation> creditNotation3);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder setMismatchResolution(CreditNotationMismatchResolutionEnum mismatchResolution);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder setReferenceAgency(CreditRatingAgencyEnum referenceAgency);
		AgencyRatingCriteria.AgencyRatingCriteriaBuilder setBoundary(CreditNotationBoundaryEnum boundary);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("qualifier"), QuantifierEnum.class, getQualifier(), this);
			processRosetta(path.newSubPath("creditNotation"), processor, CreditNotation.CreditNotationBuilder.class, getCreditNotation());
			processor.processBasic(path.newSubPath("mismatchResolution"), CreditNotationMismatchResolutionEnum.class, getMismatchResolution(), this);
			processor.processBasic(path.newSubPath("referenceAgency"), CreditRatingAgencyEnum.class, getReferenceAgency(), this);
			processor.processBasic(path.newSubPath("boundary"), CreditNotationBoundaryEnum.class, getBoundary(), this);
		}
		

		AgencyRatingCriteria.AgencyRatingCriteriaBuilder prune();
	}

	/*********************** Immutable Implementation of AgencyRatingCriteria  ***********************/
	class AgencyRatingCriteriaImpl implements AgencyRatingCriteria {
		private final QuantifierEnum qualifier;
		private final List<? extends CreditNotation> creditNotation;
		private final CreditNotationMismatchResolutionEnum mismatchResolution;
		private final CreditRatingAgencyEnum referenceAgency;
		private final CreditNotationBoundaryEnum boundary;
		
		protected AgencyRatingCriteriaImpl(AgencyRatingCriteria.AgencyRatingCriteriaBuilder builder) {
			this.qualifier = builder.getQualifier();
			this.creditNotation = ofNullable(builder.getCreditNotation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.mismatchResolution = builder.getMismatchResolution();
			this.referenceAgency = builder.getReferenceAgency();
			this.boundary = builder.getBoundary();
		}
		
		@Override
		@RosettaAttribute("qualifier")
		public QuantifierEnum getQualifier() {
			return qualifier;
		}
		
		@Override
		@RosettaAttribute("creditNotation")
		public List<? extends CreditNotation> getCreditNotation() {
			return creditNotation;
		}
		
		@Override
		@RosettaAttribute("mismatchResolution")
		public CreditNotationMismatchResolutionEnum getMismatchResolution() {
			return mismatchResolution;
		}
		
		@Override
		@RosettaAttribute("referenceAgency")
		public CreditRatingAgencyEnum getReferenceAgency() {
			return referenceAgency;
		}
		
		@Override
		@RosettaAttribute("boundary")
		public CreditNotationBoundaryEnum getBoundary() {
			return boundary;
		}
		
		@Override
		public AgencyRatingCriteria build() {
			return this;
		}
		
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder toBuilder() {
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AgencyRatingCriteria.AgencyRatingCriteriaBuilder builder) {
			ofNullable(getQualifier()).ifPresent(builder::setQualifier);
			ofNullable(getCreditNotation()).ifPresent(builder::setCreditNotation);
			ofNullable(getMismatchResolution()).ifPresent(builder::setMismatchResolution);
			ofNullable(getReferenceAgency()).ifPresent(builder::setReferenceAgency);
			ofNullable(getBoundary()).ifPresent(builder::setBoundary);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AgencyRatingCriteria _that = getType().cast(o);
		
			if (!Objects.equals(qualifier, _that.getQualifier())) return false;
			if (!ListEquals.listEquals(creditNotation, _that.getCreditNotation())) return false;
			if (!Objects.equals(mismatchResolution, _that.getMismatchResolution())) return false;
			if (!Objects.equals(referenceAgency, _that.getReferenceAgency())) return false;
			if (!Objects.equals(boundary, _that.getBoundary())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (qualifier != null ? qualifier.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditNotation != null ? creditNotation.hashCode() : 0);
			_result = 31 * _result + (mismatchResolution != null ? mismatchResolution.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (referenceAgency != null ? referenceAgency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (boundary != null ? boundary.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AgencyRatingCriteria {" +
				"qualifier=" + this.qualifier + ", " +
				"creditNotation=" + this.creditNotation + ", " +
				"mismatchResolution=" + this.mismatchResolution + ", " +
				"referenceAgency=" + this.referenceAgency + ", " +
				"boundary=" + this.boundary +
			'}';
		}
	}

	/*********************** Builder Implementation of AgencyRatingCriteria  ***********************/
	class AgencyRatingCriteriaBuilderImpl implements AgencyRatingCriteria.AgencyRatingCriteriaBuilder {
	
		protected QuantifierEnum qualifier;
		protected List<CreditNotation.CreditNotationBuilder> creditNotation = new ArrayList<>();
		protected CreditNotationMismatchResolutionEnum mismatchResolution;
		protected CreditRatingAgencyEnum referenceAgency;
		protected CreditNotationBoundaryEnum boundary;
	
		public AgencyRatingCriteriaBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("qualifier")
		public QuantifierEnum getQualifier() {
			return qualifier;
		}
		
		@Override
		@RosettaAttribute("creditNotation")
		public List<? extends CreditNotation.CreditNotationBuilder> getCreditNotation() {
			return creditNotation;
		}
		
		public CreditNotation.CreditNotationBuilder getOrCreateCreditNotation(int _index) {
		
			if (creditNotation==null) {
				this.creditNotation = new ArrayList<>();
			}
			CreditNotation.CreditNotationBuilder result;
			return getIndex(creditNotation, _index, () -> {
						CreditNotation.CreditNotationBuilder newCreditNotation = CreditNotation.builder();
						return newCreditNotation;
					});
		}
		
		@Override
		@RosettaAttribute("mismatchResolution")
		public CreditNotationMismatchResolutionEnum getMismatchResolution() {
			return mismatchResolution;
		}
		
		@Override
		@RosettaAttribute("referenceAgency")
		public CreditRatingAgencyEnum getReferenceAgency() {
			return referenceAgency;
		}
		
		@Override
		@RosettaAttribute("boundary")
		public CreditNotationBoundaryEnum getBoundary() {
			return boundary;
		}
		
	
		@Override
		@RosettaAttribute("qualifier")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder setQualifier(QuantifierEnum qualifier) {
			this.qualifier = qualifier==null?null:qualifier;
			return this;
		}
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(CreditNotation creditNotation) {
			if (creditNotation!=null) this.creditNotation.add(creditNotation.toBuilder());
			return this;
		}
		
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(CreditNotation creditNotation, int _idx) {
			getIndex(this.creditNotation, _idx, () -> creditNotation.toBuilder());
			return this;
		}
		@Override 
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder addCreditNotation(List<? extends CreditNotation> creditNotations) {
			if (creditNotations != null) {
				for (CreditNotation toAdd : creditNotations) {
					this.creditNotation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("creditNotation")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder setCreditNotation(List<? extends CreditNotation> creditNotations) {
			if (creditNotations == null)  {
				this.creditNotation = new ArrayList<>();
			}
			else {
				this.creditNotation = creditNotations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("mismatchResolution")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder setMismatchResolution(CreditNotationMismatchResolutionEnum mismatchResolution) {
			this.mismatchResolution = mismatchResolution==null?null:mismatchResolution;
			return this;
		}
		@Override
		@RosettaAttribute("referenceAgency")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder setReferenceAgency(CreditRatingAgencyEnum referenceAgency) {
			this.referenceAgency = referenceAgency==null?null:referenceAgency;
			return this;
		}
		@Override
		@RosettaAttribute("boundary")
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder setBoundary(CreditNotationBoundaryEnum boundary) {
			this.boundary = boundary==null?null:boundary;
			return this;
		}
		
		@Override
		public AgencyRatingCriteria build() {
			return new AgencyRatingCriteria.AgencyRatingCriteriaImpl(this);
		}
		
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder prune() {
			creditNotation = creditNotation.stream().filter(b->b!=null).<CreditNotation.CreditNotationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getQualifier()!=null) return true;
			if (getCreditNotation()!=null && getCreditNotation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMismatchResolution()!=null) return true;
			if (getReferenceAgency()!=null) return true;
			if (getBoundary()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AgencyRatingCriteria.AgencyRatingCriteriaBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AgencyRatingCriteria.AgencyRatingCriteriaBuilder o = (AgencyRatingCriteria.AgencyRatingCriteriaBuilder) other;
			
			merger.mergeRosetta(getCreditNotation(), o.getCreditNotation(), this::getOrCreateCreditNotation);
			
			merger.mergeBasic(getQualifier(), o.getQualifier(), this::setQualifier);
			merger.mergeBasic(getMismatchResolution(), o.getMismatchResolution(), this::setMismatchResolution);
			merger.mergeBasic(getReferenceAgency(), o.getReferenceAgency(), this::setReferenceAgency);
			merger.mergeBasic(getBoundary(), o.getBoundary(), this::setBoundary);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AgencyRatingCriteria _that = getType().cast(o);
		
			if (!Objects.equals(qualifier, _that.getQualifier())) return false;
			if (!ListEquals.listEquals(creditNotation, _that.getCreditNotation())) return false;
			if (!Objects.equals(mismatchResolution, _that.getMismatchResolution())) return false;
			if (!Objects.equals(referenceAgency, _that.getReferenceAgency())) return false;
			if (!Objects.equals(boundary, _that.getBoundary())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (qualifier != null ? qualifier.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditNotation != null ? creditNotation.hashCode() : 0);
			_result = 31 * _result + (mismatchResolution != null ? mismatchResolution.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (referenceAgency != null ? referenceAgency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (boundary != null ? boundary.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AgencyRatingCriteriaBuilder {" +
				"qualifier=" + this.qualifier + ", " +
				"creditNotation=" + this.creditNotation + ", " +
				"mismatchResolution=" + this.mismatchResolution + ", " +
				"referenceAgency=" + this.referenceAgency + ", " +
				"boundary=" + this.boundary +
			'}';
		}
	}
}
