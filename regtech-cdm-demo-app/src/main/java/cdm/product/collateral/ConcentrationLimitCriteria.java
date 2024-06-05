package cdm.product.collateral;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.CollateralCriteriaBase;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseBuilder;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseBuilderImpl;
import cdm.product.collateral.CollateralCriteriaBase.CollateralCriteriaBaseImpl;
import cdm.product.collateral.ConcentrationLimitCriteria;
import cdm.product.collateral.ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder;
import cdm.product.collateral.ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilderImpl;
import cdm.product.collateral.ConcentrationLimitCriteria.ConcentrationLimitCriteriaImpl;
import cdm.product.collateral.ConcentrationLimitTypeEnum;
import cdm.product.collateral.IssuerCriteria;
import cdm.product.collateral.meta.ConcentrationLimitCriteriaMeta;
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
 * Respresents a class to describe a set of criteria to describe specific assets that the concentration limits apply to.
 * @version ${project.version}
 */
@RosettaDataType(value="ConcentrationLimitCriteria", builder=ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilderImpl.class, version="${project.version}")
public interface ConcentrationLimitCriteria extends CollateralCriteriaBase {

	ConcentrationLimitCriteriaMeta metaData = new ConcentrationLimitCriteriaMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the type of concentration limit to be applied.
	 */
	ConcentrationLimitTypeEnum getConcentrationLimitType();
	/**
	 * Specifies an average trading volume on an exchange in relation to Equity products.
	 */
	AverageTradingVolume getAverageTradingVolume();

	/*********************** Build Methods  ***********************/
	ConcentrationLimitCriteria build();
	
	ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder toBuilder();
	
	static ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder builder() {
		return new ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ConcentrationLimitCriteria> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ConcentrationLimitCriteria> getType() {
		return ConcentrationLimitCriteria.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuer"), processor, IssuerCriteria.class, getIssuer());
		processRosetta(path.newSubPath("asset"), processor, AssetCriteria.class, getAsset());
		processor.processBasic(path.newSubPath("appliesTo"), CounterpartyRoleEnum.class, getAppliesTo(), this);
		processor.processBasic(path.newSubPath("concentrationLimitType"), ConcentrationLimitTypeEnum.class, getConcentrationLimitType(), this);
		processRosetta(path.newSubPath("averageTradingVolume"), processor, AverageTradingVolume.class, getAverageTradingVolume());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ConcentrationLimitCriteriaBuilder extends ConcentrationLimitCriteria, CollateralCriteriaBase.CollateralCriteriaBaseBuilder, RosettaModelObjectBuilder {
		AverageTradingVolume.AverageTradingVolumeBuilder getOrCreateAverageTradingVolume();
		AverageTradingVolume.AverageTradingVolumeBuilder getAverageTradingVolume();
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(IssuerCriteria issuer0);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(IssuerCriteria issuer1, int _idx);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(List<? extends IssuerCriteria> issuer2);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setIssuer(List<? extends IssuerCriteria> issuer3);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(AssetCriteria asset0);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(AssetCriteria asset1, int _idx);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(List<? extends AssetCriteria> asset2);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAsset(List<? extends AssetCriteria> asset3);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo0);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo1, int _idx);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(List<? extends CounterpartyRoleEnum> appliesTo2);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAppliesTo(List<? extends CounterpartyRoleEnum> appliesTo3);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setConcentrationLimitType(ConcentrationLimitTypeEnum concentrationLimitType);
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAverageTradingVolume(AverageTradingVolume averageTradingVolume);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuer"), processor, IssuerCriteria.IssuerCriteriaBuilder.class, getIssuer());
			processRosetta(path.newSubPath("asset"), processor, AssetCriteria.AssetCriteriaBuilder.class, getAsset());
			processor.processBasic(path.newSubPath("appliesTo"), CounterpartyRoleEnum.class, getAppliesTo(), this);
			processor.processBasic(path.newSubPath("concentrationLimitType"), ConcentrationLimitTypeEnum.class, getConcentrationLimitType(), this);
			processRosetta(path.newSubPath("averageTradingVolume"), processor, AverageTradingVolume.AverageTradingVolumeBuilder.class, getAverageTradingVolume());
		}
		

		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder prune();
	}

	/*********************** Immutable Implementation of ConcentrationLimitCriteria  ***********************/
	class ConcentrationLimitCriteriaImpl extends CollateralCriteriaBase.CollateralCriteriaBaseImpl implements ConcentrationLimitCriteria {
		private final ConcentrationLimitTypeEnum concentrationLimitType;
		private final AverageTradingVolume averageTradingVolume;
		
		protected ConcentrationLimitCriteriaImpl(ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder builder) {
			super(builder);
			this.concentrationLimitType = builder.getConcentrationLimitType();
			this.averageTradingVolume = ofNullable(builder.getAverageTradingVolume()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("concentrationLimitType")
		public ConcentrationLimitTypeEnum getConcentrationLimitType() {
			return concentrationLimitType;
		}
		
		@Override
		@RosettaAttribute("averageTradingVolume")
		public AverageTradingVolume getAverageTradingVolume() {
			return averageTradingVolume;
		}
		
		@Override
		public ConcentrationLimitCriteria build() {
			return this;
		}
		
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder toBuilder() {
			ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getConcentrationLimitType()).ifPresent(builder::setConcentrationLimitType);
			ofNullable(getAverageTradingVolume()).ifPresent(builder::setAverageTradingVolume);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ConcentrationLimitCriteria _that = getType().cast(o);
		
			if (!Objects.equals(concentrationLimitType, _that.getConcentrationLimitType())) return false;
			if (!Objects.equals(averageTradingVolume, _that.getAverageTradingVolume())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (concentrationLimitType != null ? concentrationLimitType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (averageTradingVolume != null ? averageTradingVolume.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConcentrationLimitCriteria {" +
				"concentrationLimitType=" + this.concentrationLimitType + ", " +
				"averageTradingVolume=" + this.averageTradingVolume +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ConcentrationLimitCriteria  ***********************/
	class ConcentrationLimitCriteriaBuilderImpl extends CollateralCriteriaBase.CollateralCriteriaBaseBuilderImpl  implements ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder {
	
		protected ConcentrationLimitTypeEnum concentrationLimitType;
		protected AverageTradingVolume.AverageTradingVolumeBuilder averageTradingVolume;
	
		public ConcentrationLimitCriteriaBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("concentrationLimitType")
		public ConcentrationLimitTypeEnum getConcentrationLimitType() {
			return concentrationLimitType;
		}
		
		@Override
		@RosettaAttribute("averageTradingVolume")
		public AverageTradingVolume.AverageTradingVolumeBuilder getAverageTradingVolume() {
			return averageTradingVolume;
		}
		
		@Override
		public AverageTradingVolume.AverageTradingVolumeBuilder getOrCreateAverageTradingVolume() {
			AverageTradingVolume.AverageTradingVolumeBuilder result;
			if (averageTradingVolume!=null) {
				result = averageTradingVolume;
			}
			else {
				result = averageTradingVolume = AverageTradingVolume.builder();
			}
			
			return result;
		}
	
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(IssuerCriteria issuer) {
			if (issuer!=null) this.issuer.add(issuer.toBuilder());
			return this;
		}
		
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(IssuerCriteria issuer, int _idx) {
			getIndex(this.issuer, _idx, () -> issuer.toBuilder());
			return this;
		}
		@Override 
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addIssuer(List<? extends IssuerCriteria> issuers) {
			if (issuers != null) {
				for (IssuerCriteria toAdd : issuers) {
					this.issuer.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("issuer")
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setIssuer(List<? extends IssuerCriteria> issuers) {
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
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(AssetCriteria asset) {
			if (asset!=null) this.asset.add(asset.toBuilder());
			return this;
		}
		
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(AssetCriteria asset, int _idx) {
			getIndex(this.asset, _idx, () -> asset.toBuilder());
			return this;
		}
		@Override 
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAsset(List<? extends AssetCriteria> assets) {
			if (assets != null) {
				for (AssetCriteria toAdd : assets) {
					this.asset.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("asset")
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAsset(List<? extends AssetCriteria> assets) {
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
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo) {
			if (appliesTo!=null) this.appliesTo.add(appliesTo);
			return this;
		}
		
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(CounterpartyRoleEnum appliesTo, int _idx) {
			getIndex(this.appliesTo, _idx, () -> appliesTo);
			return this;
		}
		@Override 
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder addAppliesTo(List<? extends CounterpartyRoleEnum> appliesTos) {
			if (appliesTos != null) {
				for (CounterpartyRoleEnum toAdd : appliesTos) {
					this.appliesTo.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("appliesTo")
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAppliesTo(List<? extends CounterpartyRoleEnum> appliesTos) {
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
		@RosettaAttribute("concentrationLimitType")
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setConcentrationLimitType(ConcentrationLimitTypeEnum concentrationLimitType) {
			this.concentrationLimitType = concentrationLimitType==null?null:concentrationLimitType;
			return this;
		}
		@Override
		@RosettaAttribute("averageTradingVolume")
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder setAverageTradingVolume(AverageTradingVolume averageTradingVolume) {
			this.averageTradingVolume = averageTradingVolume==null?null:averageTradingVolume.toBuilder();
			return this;
		}
		
		@Override
		public ConcentrationLimitCriteria build() {
			return new ConcentrationLimitCriteria.ConcentrationLimitCriteriaImpl(this);
		}
		
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder prune() {
			super.prune();
			if (averageTradingVolume!=null && !averageTradingVolume.prune().hasData()) averageTradingVolume = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getConcentrationLimitType()!=null) return true;
			if (getAverageTradingVolume()!=null && getAverageTradingVolume().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder o = (ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder) other;
			
			merger.mergeRosetta(getAverageTradingVolume(), o.getAverageTradingVolume(), this::setAverageTradingVolume);
			
			merger.mergeBasic(getConcentrationLimitType(), o.getConcentrationLimitType(), this::setConcentrationLimitType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			ConcentrationLimitCriteria _that = getType().cast(o);
		
			if (!Objects.equals(concentrationLimitType, _that.getConcentrationLimitType())) return false;
			if (!Objects.equals(averageTradingVolume, _that.getAverageTradingVolume())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (concentrationLimitType != null ? concentrationLimitType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (averageTradingVolume != null ? averageTradingVolume.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConcentrationLimitCriteriaBuilder {" +
				"concentrationLimitType=" + this.concentrationLimitType + ", " +
				"averageTradingVolume=" + this.averageTradingVolume +
			'}' + " " + super.toString();
		}
	}
}
