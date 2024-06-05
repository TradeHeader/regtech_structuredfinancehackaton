package cdm.observable.asset;

import cdm.observable.asset.CleanOrDirtyPrice;
import cdm.observable.asset.CleanOrDirtyPrice.CleanOrDirtyPriceBuilder;
import cdm.observable.asset.CleanOrDirtyPrice.CleanOrDirtyPriceBuilderImpl;
import cdm.observable.asset.CleanOrDirtyPrice.CleanOrDirtyPriceImpl;
import cdm.observable.asset.CleanPrice;
import cdm.observable.asset.meta.CleanOrDirtyPriceMeta;
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
 *  Class specifying the bond price as either clean or dirty in a bond valuation model.
 * @version ${project.version}
 */
@RosettaDataType(value="CleanOrDirtyPrice", builder=CleanOrDirtyPrice.CleanOrDirtyPriceBuilderImpl.class, version="${project.version}")
public interface CleanOrDirtyPrice extends RosettaModelObject {

	CleanOrDirtyPriceMeta metaData = new CleanOrDirtyPriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The clean price and accruals presented separately.
	 */
	CleanPrice getCleanPrice();
	/**
	 * The dirty price presented as a single number.
	 */
	BigDecimal getDirtyPrice();

	/*********************** Build Methods  ***********************/
	CleanOrDirtyPrice build();
	
	CleanOrDirtyPrice.CleanOrDirtyPriceBuilder toBuilder();
	
	static CleanOrDirtyPrice.CleanOrDirtyPriceBuilder builder() {
		return new CleanOrDirtyPrice.CleanOrDirtyPriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CleanOrDirtyPrice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CleanOrDirtyPrice> getType() {
		return CleanOrDirtyPrice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("cleanPrice"), processor, CleanPrice.class, getCleanPrice());
		processor.processBasic(path.newSubPath("dirtyPrice"), BigDecimal.class, getDirtyPrice(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CleanOrDirtyPriceBuilder extends CleanOrDirtyPrice, RosettaModelObjectBuilder {
		CleanPrice.CleanPriceBuilder getOrCreateCleanPrice();
		CleanPrice.CleanPriceBuilder getCleanPrice();
		CleanOrDirtyPrice.CleanOrDirtyPriceBuilder setCleanPrice(CleanPrice cleanPrice);
		CleanOrDirtyPrice.CleanOrDirtyPriceBuilder setDirtyPrice(BigDecimal dirtyPrice);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("cleanPrice"), processor, CleanPrice.CleanPriceBuilder.class, getCleanPrice());
			processor.processBasic(path.newSubPath("dirtyPrice"), BigDecimal.class, getDirtyPrice(), this);
		}
		

		CleanOrDirtyPrice.CleanOrDirtyPriceBuilder prune();
	}

	/*********************** Immutable Implementation of CleanOrDirtyPrice  ***********************/
	class CleanOrDirtyPriceImpl implements CleanOrDirtyPrice {
		private final CleanPrice cleanPrice;
		private final BigDecimal dirtyPrice;
		
		protected CleanOrDirtyPriceImpl(CleanOrDirtyPrice.CleanOrDirtyPriceBuilder builder) {
			this.cleanPrice = ofNullable(builder.getCleanPrice()).map(f->f.build()).orElse(null);
			this.dirtyPrice = builder.getDirtyPrice();
		}
		
		@Override
		@RosettaAttribute("cleanPrice")
		public CleanPrice getCleanPrice() {
			return cleanPrice;
		}
		
		@Override
		@RosettaAttribute("dirtyPrice")
		public BigDecimal getDirtyPrice() {
			return dirtyPrice;
		}
		
		@Override
		public CleanOrDirtyPrice build() {
			return this;
		}
		
		@Override
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder toBuilder() {
			CleanOrDirtyPrice.CleanOrDirtyPriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CleanOrDirtyPrice.CleanOrDirtyPriceBuilder builder) {
			ofNullable(getCleanPrice()).ifPresent(builder::setCleanPrice);
			ofNullable(getDirtyPrice()).ifPresent(builder::setDirtyPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CleanOrDirtyPrice _that = getType().cast(o);
		
			if (!Objects.equals(cleanPrice, _that.getCleanPrice())) return false;
			if (!Objects.equals(dirtyPrice, _that.getDirtyPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cleanPrice != null ? cleanPrice.hashCode() : 0);
			_result = 31 * _result + (dirtyPrice != null ? dirtyPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CleanOrDirtyPrice {" +
				"cleanPrice=" + this.cleanPrice + ", " +
				"dirtyPrice=" + this.dirtyPrice +
			'}';
		}
	}

	/*********************** Builder Implementation of CleanOrDirtyPrice  ***********************/
	class CleanOrDirtyPriceBuilderImpl implements CleanOrDirtyPrice.CleanOrDirtyPriceBuilder {
	
		protected CleanPrice.CleanPriceBuilder cleanPrice;
		protected BigDecimal dirtyPrice;
	
		public CleanOrDirtyPriceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cleanPrice")
		public CleanPrice.CleanPriceBuilder getCleanPrice() {
			return cleanPrice;
		}
		
		@Override
		public CleanPrice.CleanPriceBuilder getOrCreateCleanPrice() {
			CleanPrice.CleanPriceBuilder result;
			if (cleanPrice!=null) {
				result = cleanPrice;
			}
			else {
				result = cleanPrice = CleanPrice.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dirtyPrice")
		public BigDecimal getDirtyPrice() {
			return dirtyPrice;
		}
		
	
		@Override
		@RosettaAttribute("cleanPrice")
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder setCleanPrice(CleanPrice cleanPrice) {
			this.cleanPrice = cleanPrice==null?null:cleanPrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dirtyPrice")
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder setDirtyPrice(BigDecimal dirtyPrice) {
			this.dirtyPrice = dirtyPrice==null?null:dirtyPrice;
			return this;
		}
		
		@Override
		public CleanOrDirtyPrice build() {
			return new CleanOrDirtyPrice.CleanOrDirtyPriceImpl(this);
		}
		
		@Override
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder prune() {
			if (cleanPrice!=null && !cleanPrice.prune().hasData()) cleanPrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCleanPrice()!=null && getCleanPrice().hasData()) return true;
			if (getDirtyPrice()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CleanOrDirtyPrice.CleanOrDirtyPriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CleanOrDirtyPrice.CleanOrDirtyPriceBuilder o = (CleanOrDirtyPrice.CleanOrDirtyPriceBuilder) other;
			
			merger.mergeRosetta(getCleanPrice(), o.getCleanPrice(), this::setCleanPrice);
			
			merger.mergeBasic(getDirtyPrice(), o.getDirtyPrice(), this::setDirtyPrice);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CleanOrDirtyPrice _that = getType().cast(o);
		
			if (!Objects.equals(cleanPrice, _that.getCleanPrice())) return false;
			if (!Objects.equals(dirtyPrice, _that.getDirtyPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cleanPrice != null ? cleanPrice.hashCode() : 0);
			_result = 31 * _result + (dirtyPrice != null ? dirtyPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CleanOrDirtyPriceBuilder {" +
				"cleanPrice=" + this.cleanPrice + ", " +
				"dirtyPrice=" + this.dirtyPrice +
			'}';
		}
	}
}
