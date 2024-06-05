package cdm.observable.asset.fro;

import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder;
import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilderImpl;
import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsImpl;
import cdm.observable.asset.fro.FloatingRateIndexCalculationMethodEnum;
import cdm.observable.asset.fro.FloatingRateIndexCategoryEnum;
import cdm.observable.asset.fro.FloatingRateIndexStyleEnum;
import cdm.observable.asset.fro.meta.FloatingRateIndexCalculationDefaultsMeta;
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
 * This holds the rate calculation defaults applicable for a floating rate index.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateIndexCalculationDefaults", builder=FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilderImpl.class, version="${project.version}")
public interface FloatingRateIndexCalculationDefaults extends RosettaModelObject {

	FloatingRateIndexCalculationDefaultsMeta metaData = new FloatingRateIndexCalculationDefaultsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The ISDA FRO category (e.g. screen rate or calculated rate).
	 */
	FloatingRateIndexCategoryEnum getCategory();
	/**
	 * The ISDA FRO style (e.g. term rate, swap rate, etc).
	 */
	FloatingRateIndexStyleEnum getIndexStyle();
	/**
	 * The ISDA FRO calculation method (e.g. OIS Compounding).
	 */
	FloatingRateIndexCalculationMethodEnum getMethod();

	/*********************** Build Methods  ***********************/
	FloatingRateIndexCalculationDefaults build();
	
	FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder toBuilder();
	
	static FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder builder() {
		return new FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateIndexCalculationDefaults> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateIndexCalculationDefaults> getType() {
		return FloatingRateIndexCalculationDefaults.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("category"), FloatingRateIndexCategoryEnum.class, getCategory(), this);
		processor.processBasic(path.newSubPath("indexStyle"), FloatingRateIndexStyleEnum.class, getIndexStyle(), this);
		processor.processBasic(path.newSubPath("method"), FloatingRateIndexCalculationMethodEnum.class, getMethod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateIndexCalculationDefaultsBuilder extends FloatingRateIndexCalculationDefaults, RosettaModelObjectBuilder {
		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setCategory(FloatingRateIndexCategoryEnum category);
		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setIndexStyle(FloatingRateIndexStyleEnum indexStyle);
		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setMethod(FloatingRateIndexCalculationMethodEnum method);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("category"), FloatingRateIndexCategoryEnum.class, getCategory(), this);
			processor.processBasic(path.newSubPath("indexStyle"), FloatingRateIndexStyleEnum.class, getIndexStyle(), this);
			processor.processBasic(path.newSubPath("method"), FloatingRateIndexCalculationMethodEnum.class, getMethod(), this);
		}
		

		FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateIndexCalculationDefaults  ***********************/
	class FloatingRateIndexCalculationDefaultsImpl implements FloatingRateIndexCalculationDefaults {
		private final FloatingRateIndexCategoryEnum category;
		private final FloatingRateIndexStyleEnum indexStyle;
		private final FloatingRateIndexCalculationMethodEnum method;
		
		protected FloatingRateIndexCalculationDefaultsImpl(FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder builder) {
			this.category = builder.getCategory();
			this.indexStyle = builder.getIndexStyle();
			this.method = builder.getMethod();
		}
		
		@Override
		@RosettaAttribute("category")
		public FloatingRateIndexCategoryEnum getCategory() {
			return category;
		}
		
		@Override
		@RosettaAttribute("indexStyle")
		public FloatingRateIndexStyleEnum getIndexStyle() {
			return indexStyle;
		}
		
		@Override
		@RosettaAttribute("method")
		public FloatingRateIndexCalculationMethodEnum getMethod() {
			return method;
		}
		
		@Override
		public FloatingRateIndexCalculationDefaults build() {
			return this;
		}
		
		@Override
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder toBuilder() {
			FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder builder) {
			ofNullable(getCategory()).ifPresent(builder::setCategory);
			ofNullable(getIndexStyle()).ifPresent(builder::setIndexStyle);
			ofNullable(getMethod()).ifPresent(builder::setMethod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateIndexCalculationDefaults _that = getType().cast(o);
		
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(indexStyle, _that.getIndexStyle())) return false;
			if (!Objects.equals(method, _that.getMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexStyle != null ? indexStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (method != null ? method.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateIndexCalculationDefaults {" +
				"category=" + this.category + ", " +
				"indexStyle=" + this.indexStyle + ", " +
				"method=" + this.method +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateIndexCalculationDefaults  ***********************/
	class FloatingRateIndexCalculationDefaultsBuilderImpl implements FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder {
	
		protected FloatingRateIndexCategoryEnum category;
		protected FloatingRateIndexStyleEnum indexStyle;
		protected FloatingRateIndexCalculationMethodEnum method;
	
		public FloatingRateIndexCalculationDefaultsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("category")
		public FloatingRateIndexCategoryEnum getCategory() {
			return category;
		}
		
		@Override
		@RosettaAttribute("indexStyle")
		public FloatingRateIndexStyleEnum getIndexStyle() {
			return indexStyle;
		}
		
		@Override
		@RosettaAttribute("method")
		public FloatingRateIndexCalculationMethodEnum getMethod() {
			return method;
		}
		
	
		@Override
		@RosettaAttribute("category")
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setCategory(FloatingRateIndexCategoryEnum category) {
			this.category = category==null?null:category;
			return this;
		}
		@Override
		@RosettaAttribute("indexStyle")
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setIndexStyle(FloatingRateIndexStyleEnum indexStyle) {
			this.indexStyle = indexStyle==null?null:indexStyle;
			return this;
		}
		@Override
		@RosettaAttribute("method")
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder setMethod(FloatingRateIndexCalculationMethodEnum method) {
			this.method = method==null?null:method;
			return this;
		}
		
		@Override
		public FloatingRateIndexCalculationDefaults build() {
			return new FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsImpl(this);
		}
		
		@Override
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCategory()!=null) return true;
			if (getIndexStyle()!=null) return true;
			if (getMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder o = (FloatingRateIndexCalculationDefaults.FloatingRateIndexCalculationDefaultsBuilder) other;
			
			
			merger.mergeBasic(getCategory(), o.getCategory(), this::setCategory);
			merger.mergeBasic(getIndexStyle(), o.getIndexStyle(), this::setIndexStyle);
			merger.mergeBasic(getMethod(), o.getMethod(), this::setMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateIndexCalculationDefaults _that = getType().cast(o);
		
			if (!Objects.equals(category, _that.getCategory())) return false;
			if (!Objects.equals(indexStyle, _that.getIndexStyle())) return false;
			if (!Objects.equals(method, _that.getMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (category != null ? category.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexStyle != null ? indexStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (method != null ? method.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateIndexCalculationDefaultsBuilder {" +
				"category=" + this.category + ", " +
				"indexStyle=" + this.indexStyle + ", " +
				"method=" + this.method +
			'}';
		}
	}
}
