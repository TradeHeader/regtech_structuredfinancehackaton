package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.AssetType.AssetTypeBuilder;
import cdm.base.staticdata.asset.common.AssetType.AssetTypeBuilderImpl;
import cdm.base.staticdata.asset.common.AssetType.AssetTypeImpl;
import cdm.base.staticdata.asset.common.AssetTypeEnum;
import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
import cdm.base.staticdata.asset.common.FundProductTypeEnum;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.base.staticdata.asset.common.meta.AssetTypeMeta;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents a class to allow specification of the asset product type.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetType", builder=AssetType.AssetTypeBuilderImpl.class, version="${project.version}")
public interface AssetType extends RosettaModelObject {

	AssetTypeMeta metaData = new AssetTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a filter based on the type of collateral asset.
	 */
	AssetTypeEnum getAssetType();
	/**
	 * Represents a filter based on the type of security.
	 */
	SecurityTypeEnum getSecurityType();
	/**
	 * Represents a filter based on the type of bond.
	 */
	DebtType getDebtType();
	/**
	 * Represents a filter based on the type of equity.
	 */
	EquityTypeEnum getEquityType();
	/**
	 * Represents a filter based on the type of fund.
	 */
	FundProductTypeEnum getFundType();
	/**
	 * Specifies the eligible asset type when not enumerated.
	 */
	List<String> getOtherAssetType();

	/*********************** Build Methods  ***********************/
	AssetType build();
	
	AssetType.AssetTypeBuilder toBuilder();
	
	static AssetType.AssetTypeBuilder builder() {
		return new AssetType.AssetTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetType> getType() {
		return AssetType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("assetType"), AssetTypeEnum.class, getAssetType(), this);
		processor.processBasic(path.newSubPath("securityType"), SecurityTypeEnum.class, getSecurityType(), this);
		processRosetta(path.newSubPath("debtType"), processor, DebtType.class, getDebtType());
		processor.processBasic(path.newSubPath("equityType"), EquityTypeEnum.class, getEquityType(), this);
		processor.processBasic(path.newSubPath("fundType"), FundProductTypeEnum.class, getFundType(), this);
		processor.processBasic(path.newSubPath("otherAssetType"), String.class, getOtherAssetType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetTypeBuilder extends AssetType, RosettaModelObjectBuilder {
		DebtType.DebtTypeBuilder getOrCreateDebtType();
		DebtType.DebtTypeBuilder getDebtType();
		AssetType.AssetTypeBuilder setAssetType(AssetTypeEnum assetType);
		AssetType.AssetTypeBuilder setSecurityType(SecurityTypeEnum securityType);
		AssetType.AssetTypeBuilder setDebtType(DebtType debtType);
		AssetType.AssetTypeBuilder setEquityType(EquityTypeEnum equityType);
		AssetType.AssetTypeBuilder setFundType(FundProductTypeEnum fundType);
		AssetType.AssetTypeBuilder addOtherAssetType(String otherAssetType0);
		AssetType.AssetTypeBuilder addOtherAssetType(String otherAssetType1, int _idx);
		AssetType.AssetTypeBuilder addOtherAssetType(List<? extends String> otherAssetType2);
		AssetType.AssetTypeBuilder setOtherAssetType(List<? extends String> otherAssetType3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("assetType"), AssetTypeEnum.class, getAssetType(), this);
			processor.processBasic(path.newSubPath("securityType"), SecurityTypeEnum.class, getSecurityType(), this);
			processRosetta(path.newSubPath("debtType"), processor, DebtType.DebtTypeBuilder.class, getDebtType());
			processor.processBasic(path.newSubPath("equityType"), EquityTypeEnum.class, getEquityType(), this);
			processor.processBasic(path.newSubPath("fundType"), FundProductTypeEnum.class, getFundType(), this);
			processor.processBasic(path.newSubPath("otherAssetType"), String.class, getOtherAssetType(), this);
		}
		

		AssetType.AssetTypeBuilder prune();
	}

	/*********************** Immutable Implementation of AssetType  ***********************/
	class AssetTypeImpl implements AssetType {
		private final AssetTypeEnum assetType;
		private final SecurityTypeEnum securityType;
		private final DebtType debtType;
		private final EquityTypeEnum equityType;
		private final FundProductTypeEnum fundType;
		private final List<String> otherAssetType;
		
		protected AssetTypeImpl(AssetType.AssetTypeBuilder builder) {
			this.assetType = builder.getAssetType();
			this.securityType = builder.getSecurityType();
			this.debtType = ofNullable(builder.getDebtType()).map(f->f.build()).orElse(null);
			this.equityType = builder.getEquityType();
			this.fundType = builder.getFundType();
			this.otherAssetType = ofNullable(builder.getOtherAssetType()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("assetType")
		public AssetTypeEnum getAssetType() {
			return assetType;
		}
		
		@Override
		@RosettaAttribute("securityType")
		public SecurityTypeEnum getSecurityType() {
			return securityType;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public DebtType getDebtType() {
			return debtType;
		}
		
		@Override
		@RosettaAttribute("equityType")
		public EquityTypeEnum getEquityType() {
			return equityType;
		}
		
		@Override
		@RosettaAttribute("fundType")
		public FundProductTypeEnum getFundType() {
			return fundType;
		}
		
		@Override
		@RosettaAttribute("otherAssetType")
		public List<String> getOtherAssetType() {
			return otherAssetType;
		}
		
		@Override
		public AssetType build() {
			return this;
		}
		
		@Override
		public AssetType.AssetTypeBuilder toBuilder() {
			AssetType.AssetTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetType.AssetTypeBuilder builder) {
			ofNullable(getAssetType()).ifPresent(builder::setAssetType);
			ofNullable(getSecurityType()).ifPresent(builder::setSecurityType);
			ofNullable(getDebtType()).ifPresent(builder::setDebtType);
			ofNullable(getEquityType()).ifPresent(builder::setEquityType);
			ofNullable(getFundType()).ifPresent(builder::setFundType);
			ofNullable(getOtherAssetType()).ifPresent(builder::setOtherAssetType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetType _that = getType().cast(o);
		
			if (!Objects.equals(assetType, _that.getAssetType())) return false;
			if (!Objects.equals(securityType, _that.getSecurityType())) return false;
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(equityType, _that.getEquityType())) return false;
			if (!Objects.equals(fundType, _that.getFundType())) return false;
			if (!ListEquals.listEquals(otherAssetType, _that.getOtherAssetType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (assetType != null ? assetType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (securityType != null ? securityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (equityType != null ? equityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (fundType != null ? fundType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (otherAssetType != null ? otherAssetType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetType {" +
				"assetType=" + this.assetType + ", " +
				"securityType=" + this.securityType + ", " +
				"debtType=" + this.debtType + ", " +
				"equityType=" + this.equityType + ", " +
				"fundType=" + this.fundType + ", " +
				"otherAssetType=" + this.otherAssetType +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetType  ***********************/
	class AssetTypeBuilderImpl implements AssetType.AssetTypeBuilder {
	
		protected AssetTypeEnum assetType;
		protected SecurityTypeEnum securityType;
		protected DebtType.DebtTypeBuilder debtType;
		protected EquityTypeEnum equityType;
		protected FundProductTypeEnum fundType;
		protected List<String> otherAssetType = new ArrayList<>();
	
		public AssetTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("assetType")
		public AssetTypeEnum getAssetType() {
			return assetType;
		}
		
		@Override
		@RosettaAttribute("securityType")
		public SecurityTypeEnum getSecurityType() {
			return securityType;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public DebtType.DebtTypeBuilder getDebtType() {
			return debtType;
		}
		
		@Override
		public DebtType.DebtTypeBuilder getOrCreateDebtType() {
			DebtType.DebtTypeBuilder result;
			if (debtType!=null) {
				result = debtType;
			}
			else {
				result = debtType = DebtType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("equityType")
		public EquityTypeEnum getEquityType() {
			return equityType;
		}
		
		@Override
		@RosettaAttribute("fundType")
		public FundProductTypeEnum getFundType() {
			return fundType;
		}
		
		@Override
		@RosettaAttribute("otherAssetType")
		public List<String> getOtherAssetType() {
			return otherAssetType;
		}
		
	
		@Override
		@RosettaAttribute("assetType")
		public AssetType.AssetTypeBuilder setAssetType(AssetTypeEnum assetType) {
			this.assetType = assetType==null?null:assetType;
			return this;
		}
		@Override
		@RosettaAttribute("securityType")
		public AssetType.AssetTypeBuilder setSecurityType(SecurityTypeEnum securityType) {
			this.securityType = securityType==null?null:securityType;
			return this;
		}
		@Override
		@RosettaAttribute("debtType")
		public AssetType.AssetTypeBuilder setDebtType(DebtType debtType) {
			this.debtType = debtType==null?null:debtType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityType")
		public AssetType.AssetTypeBuilder setEquityType(EquityTypeEnum equityType) {
			this.equityType = equityType==null?null:equityType;
			return this;
		}
		@Override
		@RosettaAttribute("fundType")
		public AssetType.AssetTypeBuilder setFundType(FundProductTypeEnum fundType) {
			this.fundType = fundType==null?null:fundType;
			return this;
		}
		@Override
		public AssetType.AssetTypeBuilder addOtherAssetType(String otherAssetType) {
			if (otherAssetType!=null) this.otherAssetType.add(otherAssetType);
			return this;
		}
		
		@Override
		public AssetType.AssetTypeBuilder addOtherAssetType(String otherAssetType, int _idx) {
			getIndex(this.otherAssetType, _idx, () -> otherAssetType);
			return this;
		}
		@Override 
		public AssetType.AssetTypeBuilder addOtherAssetType(List<? extends String> otherAssetTypes) {
			if (otherAssetTypes != null) {
				for (String toAdd : otherAssetTypes) {
					this.otherAssetType.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("otherAssetType")
		public AssetType.AssetTypeBuilder setOtherAssetType(List<? extends String> otherAssetTypes) {
			if (otherAssetTypes == null)  {
				this.otherAssetType = new ArrayList<>();
			}
			else {
				this.otherAssetType = otherAssetTypes.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public AssetType build() {
			return new AssetType.AssetTypeImpl(this);
		}
		
		@Override
		public AssetType.AssetTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetType.AssetTypeBuilder prune() {
			if (debtType!=null && !debtType.prune().hasData()) debtType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAssetType()!=null) return true;
			if (getSecurityType()!=null) return true;
			if (getDebtType()!=null && getDebtType().hasData()) return true;
			if (getEquityType()!=null) return true;
			if (getFundType()!=null) return true;
			if (getOtherAssetType()!=null && !getOtherAssetType().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetType.AssetTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetType.AssetTypeBuilder o = (AssetType.AssetTypeBuilder) other;
			
			merger.mergeRosetta(getDebtType(), o.getDebtType(), this::setDebtType);
			
			merger.mergeBasic(getAssetType(), o.getAssetType(), this::setAssetType);
			merger.mergeBasic(getSecurityType(), o.getSecurityType(), this::setSecurityType);
			merger.mergeBasic(getEquityType(), o.getEquityType(), this::setEquityType);
			merger.mergeBasic(getFundType(), o.getFundType(), this::setFundType);
			merger.mergeBasic(getOtherAssetType(), o.getOtherAssetType(), (Consumer<String>) this::addOtherAssetType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetType _that = getType().cast(o);
		
			if (!Objects.equals(assetType, _that.getAssetType())) return false;
			if (!Objects.equals(securityType, _that.getSecurityType())) return false;
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(equityType, _that.getEquityType())) return false;
			if (!Objects.equals(fundType, _that.getFundType())) return false;
			if (!ListEquals.listEquals(otherAssetType, _that.getOtherAssetType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (assetType != null ? assetType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (securityType != null ? securityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (equityType != null ? equityType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (fundType != null ? fundType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (otherAssetType != null ? otherAssetType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetTypeBuilder {" +
				"assetType=" + this.assetType + ", " +
				"securityType=" + this.securityType + ", " +
				"debtType=" + this.debtType + ", " +
				"equityType=" + this.equityType + ", " +
				"fundType=" + this.fundType + ", " +
				"otherAssetType=" + this.otherAssetType +
			'}';
		}
	}
}
