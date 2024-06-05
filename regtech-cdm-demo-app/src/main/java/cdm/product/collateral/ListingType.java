package cdm.product.collateral;

import cdm.base.staticdata.asset.common.Index;
import cdm.product.collateral.ListingType;
import cdm.product.collateral.ListingType.ListingTypeBuilder;
import cdm.product.collateral.ListingType.ListingTypeBuilderImpl;
import cdm.product.collateral.ListingType.ListingTypeImpl;
import cdm.product.collateral.meta.ListingTypeMeta;
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
 * Specifies a filter based on an underlying corporate financial official listing defined at a stock exchange.
 * @version ${project.version}
 */
@RosettaDataType(value="ListingType", builder=ListingType.ListingTypeBuilderImpl.class, version="${project.version}")
public interface ListingType extends RosettaModelObject {

	ListingTypeMeta metaData = new ListingTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a filter based on the Primary Stock Exchange facilitating the listing of companies, exchange of Stocks, Exchange traded Derivatives, Bonds, and other Securities expressed in ISO standard 10383.
	 */
	FieldWithMetaString getExchange();
	/**
	 * Represents a filter based on an industry sector defined under a system for classifying industry types such as Global Industry Classification Standard (GICS) and North American Industry Classification System (NAICS)
	 */
	FieldWithMetaString getSector();
	/**
	 * Represents a filter based on an index that measures a stock market, or a subset of a stock market.
	 */
	Index getIndex();

	/*********************** Build Methods  ***********************/
	ListingType build();
	
	ListingType.ListingTypeBuilder toBuilder();
	
	static ListingType.ListingTypeBuilder builder() {
		return new ListingType.ListingTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ListingType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ListingType> getType() {
		return ListingType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("exchange"), processor, FieldWithMetaString.class, getExchange());
		processRosetta(path.newSubPath("sector"), processor, FieldWithMetaString.class, getSector());
		processRosetta(path.newSubPath("index"), processor, Index.class, getIndex());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ListingTypeBuilder extends ListingType, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateExchange();
		FieldWithMetaString.FieldWithMetaStringBuilder getExchange();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSector();
		FieldWithMetaString.FieldWithMetaStringBuilder getSector();
		Index.IndexBuilder getOrCreateIndex();
		Index.IndexBuilder getIndex();
		ListingType.ListingTypeBuilder setExchange(FieldWithMetaString exchange0);
		ListingType.ListingTypeBuilder setExchangeValue(String exchange1);
		ListingType.ListingTypeBuilder setSector(FieldWithMetaString sector0);
		ListingType.ListingTypeBuilder setSectorValue(String sector1);
		ListingType.ListingTypeBuilder setIndex(Index index);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("exchange"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getExchange());
			processRosetta(path.newSubPath("sector"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSector());
			processRosetta(path.newSubPath("index"), processor, Index.IndexBuilder.class, getIndex());
		}
		

		ListingType.ListingTypeBuilder prune();
	}

	/*********************** Immutable Implementation of ListingType  ***********************/
	class ListingTypeImpl implements ListingType {
		private final FieldWithMetaString exchange;
		private final FieldWithMetaString sector;
		private final Index index;
		
		protected ListingTypeImpl(ListingType.ListingTypeBuilder builder) {
			this.exchange = ofNullable(builder.getExchange()).map(f->f.build()).orElse(null);
			this.sector = ofNullable(builder.getSector()).map(f->f.build()).orElse(null);
			this.index = ofNullable(builder.getIndex()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("exchange")
		public FieldWithMetaString getExchange() {
			return exchange;
		}
		
		@Override
		@RosettaAttribute("sector")
		public FieldWithMetaString getSector() {
			return sector;
		}
		
		@Override
		@RosettaAttribute("index")
		public Index getIndex() {
			return index;
		}
		
		@Override
		public ListingType build() {
			return this;
		}
		
		@Override
		public ListingType.ListingTypeBuilder toBuilder() {
			ListingType.ListingTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ListingType.ListingTypeBuilder builder) {
			ofNullable(getExchange()).ifPresent(builder::setExchange);
			ofNullable(getSector()).ifPresent(builder::setSector);
			ofNullable(getIndex()).ifPresent(builder::setIndex);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ListingType _that = getType().cast(o);
		
			if (!Objects.equals(exchange, _that.getExchange())) return false;
			if (!Objects.equals(sector, _that.getSector())) return false;
			if (!Objects.equals(index, _that.getIndex())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exchange != null ? exchange.hashCode() : 0);
			_result = 31 * _result + (sector != null ? sector.hashCode() : 0);
			_result = 31 * _result + (index != null ? index.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ListingType {" +
				"exchange=" + this.exchange + ", " +
				"sector=" + this.sector + ", " +
				"index=" + this.index +
			'}';
		}
	}

	/*********************** Builder Implementation of ListingType  ***********************/
	class ListingTypeBuilderImpl implements ListingType.ListingTypeBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder exchange;
		protected FieldWithMetaString.FieldWithMetaStringBuilder sector;
		protected Index.IndexBuilder index;
	
		public ListingTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("exchange")
		public FieldWithMetaString.FieldWithMetaStringBuilder getExchange() {
			return exchange;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateExchange() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (exchange!=null) {
				result = exchange;
			}
			else {
				result = exchange = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sector")
		public FieldWithMetaString.FieldWithMetaStringBuilder getSector() {
			return sector;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSector() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (sector!=null) {
				result = sector;
			}
			else {
				result = sector = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("index")
		public Index.IndexBuilder getIndex() {
			return index;
		}
		
		@Override
		public Index.IndexBuilder getOrCreateIndex() {
			Index.IndexBuilder result;
			if (index!=null) {
				result = index;
			}
			else {
				result = index = Index.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("exchange")
		public ListingType.ListingTypeBuilder setExchange(FieldWithMetaString exchange) {
			this.exchange = exchange==null?null:exchange.toBuilder();
			return this;
		}
		@Override
		public ListingType.ListingTypeBuilder setExchangeValue(String exchange) {
			this.getOrCreateExchange().setValue(exchange);
			return this;
		}
		@Override
		@RosettaAttribute("sector")
		public ListingType.ListingTypeBuilder setSector(FieldWithMetaString sector) {
			this.sector = sector==null?null:sector.toBuilder();
			return this;
		}
		@Override
		public ListingType.ListingTypeBuilder setSectorValue(String sector) {
			this.getOrCreateSector().setValue(sector);
			return this;
		}
		@Override
		@RosettaAttribute("index")
		public ListingType.ListingTypeBuilder setIndex(Index index) {
			this.index = index==null?null:index.toBuilder();
			return this;
		}
		
		@Override
		public ListingType build() {
			return new ListingType.ListingTypeImpl(this);
		}
		
		@Override
		public ListingType.ListingTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ListingType.ListingTypeBuilder prune() {
			if (exchange!=null && !exchange.prune().hasData()) exchange = null;
			if (sector!=null && !sector.prune().hasData()) sector = null;
			if (index!=null && !index.prune().hasData()) index = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExchange()!=null) return true;
			if (getSector()!=null) return true;
			if (getIndex()!=null && getIndex().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ListingType.ListingTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ListingType.ListingTypeBuilder o = (ListingType.ListingTypeBuilder) other;
			
			merger.mergeRosetta(getExchange(), o.getExchange(), this::setExchange);
			merger.mergeRosetta(getSector(), o.getSector(), this::setSector);
			merger.mergeRosetta(getIndex(), o.getIndex(), this::setIndex);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ListingType _that = getType().cast(o);
		
			if (!Objects.equals(exchange, _that.getExchange())) return false;
			if (!Objects.equals(sector, _that.getSector())) return false;
			if (!Objects.equals(index, _that.getIndex())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exchange != null ? exchange.hashCode() : 0);
			_result = 31 * _result + (sector != null ? sector.hashCode() : 0);
			_result = 31 * _result + (index != null ? index.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ListingTypeBuilder {" +
				"exchange=" + this.exchange + ", " +
				"sector=" + this.sector + ", " +
				"index=" + this.index +
			'}';
		}
	}
}
