package cdm.product.collateral;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.CollateralCriteriaBase;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseBuilder;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseBuilderImpl;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseImpl;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder;
import cdm.product.collateral.EligibleCollateralCriteria.EligibleCollateralCriteriaBuilderImpl;
import cdm.product.collateral.EligibleCollateralCriteria.EligibleCollateralCriteriaImpl;
import cdm.product.collateral.IssuerCriteria;
import cdm.product.collateral.meta.EligibleCollateralCriteriaMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents a set of criteria used to specify eligible collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="EligibleCollateralCriteria", builder=EligibleCollateralCriteria.EligibleCollateralCriteriaBuilderImpl.class, version="${project.version}")
public interface EligibleCollateralCriteria extends CollateralCriteriaBase {

	EligibleCollateralCriteriaMeta metaData = new EligibleCollateralCriteriaMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the treatment of specified collateral, e.g., haircuts,holding limits or exclusions.
	 */
	CollateralTreatment getTreatment();

	/*********************** Build Methods  ***********************/
	EligibleCollateralCriteria build();
	
	EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder toBuilder();
	
	static EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder builder() {
		return new EligibleCollateralCriteria.EligibleCollateralCriteriaBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EligibleCollateralCriteria> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EligibleCollateralCriteria> getType() {
		return EligibleCollateralCriteria.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuer"), processor, IssuerCriteria.class, getIssuer());
		processRosetta(path.newSubPath("asset"), processor, AssetCriteria.class, getAsset());
		processor.processBasic(path.newSubPath("appliesTo"), CounterpartyRoleEnum.class, getAppliesTo(), this);
		processRosetta(path.newSubPath("treatment"), processor, CollateralTreatment.class, getTreatment());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EligibleCollateralCriteriaBuilder extends EligibleCollateralCriteria, CollateralCriteriaBase.CollateralCriteriaBaseBuilder, RosettaModelObjectBuilder {
		CollateralTreatment.CollateralTreatmentBuilder getOrCreateTreatment();
		CollateralTreatment.CollateralTreatmentBuilder getTreatment();
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(IssuerCriteria issuer0);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(IssuerCriteria issuer1, int _idx);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(List<? extends IssuerCriteria> issuer2);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setIssuer(List<? extends IssuerCriteria> issuer3);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(AssetCriteria asset0);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(AssetCriteria asset1, int _idx);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(List<? extends AssetCriteria> asset2);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setAsset(List<? extends AssetCriteria> asset3);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo0);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo1, int _idx);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(List<? extends CounterpartyRoleEnum> appliesTo2);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setAppliesTo(List<? extends CounterpartyRoleEnum> appliesTo3);
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setTreatment(CollateralTreatment treatment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuer"), processor, IssuerCriteria.IssuerCriteriaBuilder.class, getIssuer());
			processRosetta(path.newSubPath("asset"), processor, AssetCriteria.AssetCriteriaBuilder.class, getAsset());
			processor.processBasic(path.newSubPath("appliesTo"), CounterpartyRoleEnum.class, getAppliesTo(), this);
			processRosetta(path.newSubPath("treatment"), processor, CollateralTreatment.CollateralTreatmentBuilder.class, getTreatment());
		}
		

		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder prune();
	}

	/*********************** Immutable Implementation of EligibleCollateralCriteria  ***********************/
	class EligibleCollateralCriteriaImpl extends CollateralCriteriaBase.CollateralCriteriaBaseImpl implements EligibleCollateralCriteria {
		private final CollateralTreatment treatment;
		
		protected EligibleCollateralCriteriaImpl(EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder builder) {
			super(builder);
			this.treatment = ofNullable(builder.getTreatment()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("treatment")
		public CollateralTreatment getTreatment() {
			return treatment;
		}
		
		@Override
		public EligibleCollateralCriteria build() {
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder toBuilder() {
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getTreatment()).ifPresent(builder::setTreatment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			EligibleCollateralCriteria _that = getType().cast(o);
		
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (treatment != null ? treatment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibleCollateralCriteria {" +
				"treatment=" + this.treatment +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of EligibleCollateralCriteria  ***********************/
	class EligibleCollateralCriteriaBuilderImpl extends CollateralCriteriaBase.CollateralCriteriaBaseBuilderImpl  implements EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder {
	
		protected CollateralTreatment.CollateralTreatmentBuilder treatment;
	
		public EligibleCollateralCriteriaBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("treatment")
		public CollateralTreatment.CollateralTreatmentBuilder getTreatment() {
			return treatment;
		}
		
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder getOrCreateTreatment() {
			CollateralTreatment.CollateralTreatmentBuilder result;
			if (treatment!=null) {
				result = treatment;
			}
			else {
				result = treatment = CollateralTreatment.builder();
			}
			
			return result;
		}
	
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(IssuerCriteria issuer) {
			if (issuer!=null) this.issuer.add(issuer.toBuilder());
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(IssuerCriteria issuer, int _idx) {
			getIndex(this.issuer, _idx, () -> issuer.toBuilder());
			return this;
		}
		@Override 
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addIssuer(List<? extends IssuerCriteria> issuers) {
			if (issuers != null) {
				for (IssuerCriteria toAdd : issuers) {
					this.issuer.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuer")
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setIssuer(List<? extends IssuerCriteria> issuers) {
			if (issuers == null)  {
				this.issuer = new ArrayList<>();
			}
			else {
				this.issuer = issuers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(AssetCriteria asset) {
			if (asset!=null) this.asset.add(asset.toBuilder());
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(AssetCriteria asset, int _idx) {
			getIndex(this.asset, _idx, () -> asset.toBuilder());
			return this;
		}
		@Override 
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAsset(List<? extends AssetCriteria> assets) {
			if (assets != null) {
				for (AssetCriteria toAdd : assets) {
					this.asset.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("asset")
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setAsset(List<? extends AssetCriteria> assets) {
			if (assets == null)  {
				this.asset = new ArrayList<>();
			}
			else {
				this.asset = assets.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo) {
			if (appliesTo!=null) this.appliesTo.add(appliesTo);
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo, int _idx) {
			getIndex(this.appliesTo, _idx, () -> appliesTo);
			return this;
		}
		@Override 
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder addAppliesTo(List<? extends CounterpartyRoleEnum> appliesTos) {
			if (appliesTos != null) {
				for (CounterpartyRoleEnum toAdd : appliesTos) {
					this.appliesTo.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("appliesTo")
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setAppliesTo(List<? extends CounterpartyRoleEnum> appliesTos) {
			if (appliesTos == null)  {
				this.appliesTo = new ArrayList<>();
			}
			else {
				this.appliesTo = appliesTos.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("treatment")
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder setTreatment(CollateralTreatment treatment) {
			this.treatment = treatment==null?null:treatment.toBuilder();
			return this;
		}
		
		@Override
		public EligibleCollateralCriteria build() {
			return new EligibleCollateralCriteria.EligibleCollateralCriteriaImpl(this);
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder prune() {
			super.prune();
			if (treatment!=null && !treatment.prune().hasData()) treatment = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getTreatment()!=null && getTreatment().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder o = (EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder) other;
			
			merger.mergeRosetta(getTreatment(), o.getTreatment(), this::setTreatment);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			EligibleCollateralCriteria _that = getType().cast(o);
		
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (treatment != null ? treatment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EligibleCollateralCriteriaBuilder {" +
				"treatment=" + this.treatment +
			'}' + " " + super.toString();
		}
	}
}
