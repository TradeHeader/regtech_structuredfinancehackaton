package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.CommodityInformationPublisherEnum;
import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.CommodityProductDefinition.CommodityProductDefinitionBuilder;
import cdm.base.staticdata.asset.common.CommodityProductDefinition.CommodityProductDefinitionBuilderImpl;
import cdm.base.staticdata.asset.common.CommodityProductDefinition.CommodityProductDefinitionImpl;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import cdm.base.staticdata.asset.common.PriceSource;
import cdm.base.staticdata.asset.common.meta.CommodityProductDefinitionMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies the commodity underlier in the event that no ISDA Commodity Reference Price exists.
 * @version ${project.version}
 */
@RosettaDataType(value="CommodityProductDefinition", builder=CommodityProductDefinition.CommodityProductDefinitionBuilderImpl.class, version="${project.version}")
public interface CommodityProductDefinition extends RosettaModelObject {

	CommodityProductDefinitionMeta metaData = new CommodityProductDefinitionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the type of commodity.
	 */
	CommodityReferenceFramework getReferenceFramework();
	/**
	 * Specifies a publication that provides the commodity price, including, where applicable the details of where in the publication the price is published.  Applicable when the commodity reference price is not a futures contract
	 */
	PriceSource getPriceSource();
	/**
	 * Specifies the publication where the commodity prices can be found.
	 */
	CommodityInformationPublisherEnum getCommodityInfoPublisher();
	/**
	 *  Identifies the exchange from which the reference price should be sourced, using the scheme at the following url: http://www.fpml.org/coding-scheme/external/exchange-id-MIC-1-0
	 */
	FieldWithMetaString getExchangeId();

	/*********************** Build Methods  ***********************/
	CommodityProductDefinition build();
	
	CommodityProductDefinition.CommodityProductDefinitionBuilder toBuilder();
	
	static CommodityProductDefinition.CommodityProductDefinitionBuilder builder() {
		return new CommodityProductDefinition.CommodityProductDefinitionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CommodityProductDefinition> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CommodityProductDefinition> getType() {
		return CommodityProductDefinition.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referenceFramework"), processor, CommodityReferenceFramework.class, getReferenceFramework());
		processRosetta(path.newSubPath("priceSource"), processor, PriceSource.class, getPriceSource());
		processor.processBasic(path.newSubPath("commodityInfoPublisher"), CommodityInformationPublisherEnum.class, getCommodityInfoPublisher(), this);
		processRosetta(path.newSubPath("exchangeId"), processor, FieldWithMetaString.class, getExchangeId());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CommodityProductDefinitionBuilder extends CommodityProductDefinition, RosettaModelObjectBuilder {
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder getOrCreateReferenceFramework();
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder getReferenceFramework();
		PriceSource.PriceSourceBuilder getOrCreatePriceSource();
		PriceSource.PriceSourceBuilder getPriceSource();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateExchangeId();
		FieldWithMetaString.FieldWithMetaStringBuilder getExchangeId();
		CommodityProductDefinition.CommodityProductDefinitionBuilder setReferenceFramework(CommodityReferenceFramework referenceFramework);
		CommodityProductDefinition.CommodityProductDefinitionBuilder setPriceSource(PriceSource priceSource);
		CommodityProductDefinition.CommodityProductDefinitionBuilder setCommodityInfoPublisher(CommodityInformationPublisherEnum commodityInfoPublisher);
		CommodityProductDefinition.CommodityProductDefinitionBuilder setExchangeId(FieldWithMetaString exchangeId0);
		CommodityProductDefinition.CommodityProductDefinitionBuilder setExchangeIdValue(String exchangeId1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referenceFramework"), processor, CommodityReferenceFramework.CommodityReferenceFrameworkBuilder.class, getReferenceFramework());
			processRosetta(path.newSubPath("priceSource"), processor, PriceSource.PriceSourceBuilder.class, getPriceSource());
			processor.processBasic(path.newSubPath("commodityInfoPublisher"), CommodityInformationPublisherEnum.class, getCommodityInfoPublisher(), this);
			processRosetta(path.newSubPath("exchangeId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getExchangeId());
		}
		

		CommodityProductDefinition.CommodityProductDefinitionBuilder prune();
	}

	/*********************** Immutable Implementation of CommodityProductDefinition  ***********************/
	class CommodityProductDefinitionImpl implements CommodityProductDefinition {
		private final CommodityReferenceFramework referenceFramework;
		private final PriceSource priceSource;
		private final CommodityInformationPublisherEnum commodityInfoPublisher;
		private final FieldWithMetaString exchangeId;
		
		protected CommodityProductDefinitionImpl(CommodityProductDefinition.CommodityProductDefinitionBuilder builder) {
			this.referenceFramework = ofNullable(builder.getReferenceFramework()).map(f->f.build()).orElse(null);
			this.priceSource = ofNullable(builder.getPriceSource()).map(f->f.build()).orElse(null);
			this.commodityInfoPublisher = builder.getCommodityInfoPublisher();
			this.exchangeId = ofNullable(builder.getExchangeId()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("referenceFramework")
		public CommodityReferenceFramework getReferenceFramework() {
			return referenceFramework;
		}
		
		@Override
		@RosettaAttribute("priceSource")
		public PriceSource getPriceSource() {
			return priceSource;
		}
		
		@Override
		@RosettaAttribute("commodityInfoPublisher")
		public CommodityInformationPublisherEnum getCommodityInfoPublisher() {
			return commodityInfoPublisher;
		}
		
		@Override
		@RosettaAttribute("exchangeId")
		public FieldWithMetaString getExchangeId() {
			return exchangeId;
		}
		
		@Override
		public CommodityProductDefinition build() {
			return this;
		}
		
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder toBuilder() {
			CommodityProductDefinition.CommodityProductDefinitionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CommodityProductDefinition.CommodityProductDefinitionBuilder builder) {
			ofNullable(getReferenceFramework()).ifPresent(builder::setReferenceFramework);
			ofNullable(getPriceSource()).ifPresent(builder::setPriceSource);
			ofNullable(getCommodityInfoPublisher()).ifPresent(builder::setCommodityInfoPublisher);
			ofNullable(getExchangeId()).ifPresent(builder::setExchangeId);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CommodityProductDefinition _that = getType().cast(o);
		
			if (!Objects.equals(referenceFramework, _that.getReferenceFramework())) return false;
			if (!Objects.equals(priceSource, _that.getPriceSource())) return false;
			if (!Objects.equals(commodityInfoPublisher, _that.getCommodityInfoPublisher())) return false;
			if (!Objects.equals(exchangeId, _that.getExchangeId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceFramework != null ? referenceFramework.hashCode() : 0);
			_result = 31 * _result + (priceSource != null ? priceSource.hashCode() : 0);
			_result = 31 * _result + (commodityInfoPublisher != null ? commodityInfoPublisher.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exchangeId != null ? exchangeId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityProductDefinition {" +
				"referenceFramework=" + this.referenceFramework + ", " +
				"priceSource=" + this.priceSource + ", " +
				"commodityInfoPublisher=" + this.commodityInfoPublisher + ", " +
				"exchangeId=" + this.exchangeId +
			'}';
		}
	}

	/*********************** Builder Implementation of CommodityProductDefinition  ***********************/
	class CommodityProductDefinitionBuilderImpl implements CommodityProductDefinition.CommodityProductDefinitionBuilder {
	
		protected CommodityReferenceFramework.CommodityReferenceFrameworkBuilder referenceFramework;
		protected PriceSource.PriceSourceBuilder priceSource;
		protected CommodityInformationPublisherEnum commodityInfoPublisher;
		protected FieldWithMetaString.FieldWithMetaStringBuilder exchangeId;
	
		public CommodityProductDefinitionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("referenceFramework")
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder getReferenceFramework() {
			return referenceFramework;
		}
		
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder getOrCreateReferenceFramework() {
			CommodityReferenceFramework.CommodityReferenceFrameworkBuilder result;
			if (referenceFramework!=null) {
				result = referenceFramework;
			}
			else {
				result = referenceFramework = CommodityReferenceFramework.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceSource")
		public PriceSource.PriceSourceBuilder getPriceSource() {
			return priceSource;
		}
		
		@Override
		public PriceSource.PriceSourceBuilder getOrCreatePriceSource() {
			PriceSource.PriceSourceBuilder result;
			if (priceSource!=null) {
				result = priceSource;
			}
			else {
				result = priceSource = PriceSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commodityInfoPublisher")
		public CommodityInformationPublisherEnum getCommodityInfoPublisher() {
			return commodityInfoPublisher;
		}
		
		@Override
		@RosettaAttribute("exchangeId")
		public FieldWithMetaString.FieldWithMetaStringBuilder getExchangeId() {
			return exchangeId;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateExchangeId() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (exchangeId!=null) {
				result = exchangeId;
			}
			else {
				result = exchangeId = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("referenceFramework")
		public CommodityProductDefinition.CommodityProductDefinitionBuilder setReferenceFramework(CommodityReferenceFramework referenceFramework) {
			this.referenceFramework = referenceFramework==null?null:referenceFramework.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceSource")
		public CommodityProductDefinition.CommodityProductDefinitionBuilder setPriceSource(PriceSource priceSource) {
			this.priceSource = priceSource==null?null:priceSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commodityInfoPublisher")
		public CommodityProductDefinition.CommodityProductDefinitionBuilder setCommodityInfoPublisher(CommodityInformationPublisherEnum commodityInfoPublisher) {
			this.commodityInfoPublisher = commodityInfoPublisher==null?null:commodityInfoPublisher;
			return this;
		}
		@Override
		@RosettaAttribute("exchangeId")
		public CommodityProductDefinition.CommodityProductDefinitionBuilder setExchangeId(FieldWithMetaString exchangeId) {
			this.exchangeId = exchangeId==null?null:exchangeId.toBuilder();
			return this;
		}
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder setExchangeIdValue(String exchangeId) {
			this.getOrCreateExchangeId().setValue(exchangeId);
			return this;
		}
		
		@Override
		public CommodityProductDefinition build() {
			return new CommodityProductDefinition.CommodityProductDefinitionImpl(this);
		}
		
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder prune() {
			if (referenceFramework!=null && !referenceFramework.prune().hasData()) referenceFramework = null;
			if (priceSource!=null && !priceSource.prune().hasData()) priceSource = null;
			if (exchangeId!=null && !exchangeId.prune().hasData()) exchangeId = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferenceFramework()!=null && getReferenceFramework().hasData()) return true;
			if (getPriceSource()!=null && getPriceSource().hasData()) return true;
			if (getCommodityInfoPublisher()!=null) return true;
			if (getExchangeId()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityProductDefinition.CommodityProductDefinitionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CommodityProductDefinition.CommodityProductDefinitionBuilder o = (CommodityProductDefinition.CommodityProductDefinitionBuilder) other;
			
			merger.mergeRosetta(getReferenceFramework(), o.getReferenceFramework(), this::setReferenceFramework);
			merger.mergeRosetta(getPriceSource(), o.getPriceSource(), this::setPriceSource);
			merger.mergeRosetta(getExchangeId(), o.getExchangeId(), this::setExchangeId);
			
			merger.mergeBasic(getCommodityInfoPublisher(), o.getCommodityInfoPublisher(), this::setCommodityInfoPublisher);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CommodityProductDefinition _that = getType().cast(o);
		
			if (!Objects.equals(referenceFramework, _that.getReferenceFramework())) return false;
			if (!Objects.equals(priceSource, _that.getPriceSource())) return false;
			if (!Objects.equals(commodityInfoPublisher, _that.getCommodityInfoPublisher())) return false;
			if (!Objects.equals(exchangeId, _that.getExchangeId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceFramework != null ? referenceFramework.hashCode() : 0);
			_result = 31 * _result + (priceSource != null ? priceSource.hashCode() : 0);
			_result = 31 * _result + (commodityInfoPublisher != null ? commodityInfoPublisher.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exchangeId != null ? exchangeId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityProductDefinitionBuilder {" +
				"referenceFramework=" + this.referenceFramework + ", " +
				"priceSource=" + this.priceSource + ", " +
				"commodityInfoPublisher=" + this.commodityInfoPublisher + ", " +
				"exchangeId=" + this.exchangeId +
			'}';
		}
	}
}
