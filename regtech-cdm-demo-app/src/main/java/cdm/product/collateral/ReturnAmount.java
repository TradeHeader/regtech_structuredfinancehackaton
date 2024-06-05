package cdm.product.collateral;

import cdm.product.collateral.ReturnAmount;
import cdm.product.collateral.ReturnAmount.ReturnAmountBuilder;
import cdm.product.collateral.ReturnAmount.ReturnAmountBuilderImpl;
import cdm.product.collateral.ReturnAmount.ReturnAmountImpl;
import cdm.product.collateral.meta.ReturnAmountMeta;
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
 * A class to specify the application of Interest Amount with respect the Return Amount.
 * @version ${project.version}
 */
@RosettaDataType(value="ReturnAmount", builder=ReturnAmount.ReturnAmountBuilderImpl.class, version="${project.version}")
public interface ReturnAmount extends RosettaModelObject {

	ReturnAmountMeta metaData = new ReturnAmountMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Default language is included when True, and excluded when False.
	 */
	Boolean getIncludesDefaultLanguage();
	/**
	 * Custom election that might be specified by the parties to the agreement.
	 */
	String getCustomElection();

	/*********************** Build Methods  ***********************/
	ReturnAmount build();
	
	ReturnAmount.ReturnAmountBuilder toBuilder();
	
	static ReturnAmount.ReturnAmountBuilder builder() {
		return new ReturnAmount.ReturnAmountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReturnAmount> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReturnAmount> getType() {
		return ReturnAmount.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("includesDefaultLanguage"), Boolean.class, getIncludesDefaultLanguage(), this);
		processor.processBasic(path.newSubPath("customElection"), String.class, getCustomElection(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReturnAmountBuilder extends ReturnAmount, RosettaModelObjectBuilder {
		ReturnAmount.ReturnAmountBuilder setIncludesDefaultLanguage(Boolean includesDefaultLanguage);
		ReturnAmount.ReturnAmountBuilder setCustomElection(String customElection);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("includesDefaultLanguage"), Boolean.class, getIncludesDefaultLanguage(), this);
			processor.processBasic(path.newSubPath("customElection"), String.class, getCustomElection(), this);
		}
		

		ReturnAmount.ReturnAmountBuilder prune();
	}

	/*********************** Immutable Implementation of ReturnAmount  ***********************/
	class ReturnAmountImpl implements ReturnAmount {
		private final Boolean includesDefaultLanguage;
		private final String customElection;
		
		protected ReturnAmountImpl(ReturnAmount.ReturnAmountBuilder builder) {
			this.includesDefaultLanguage = builder.getIncludesDefaultLanguage();
			this.customElection = builder.getCustomElection();
		}
		
		@Override
		@RosettaAttribute("includesDefaultLanguage")
		public Boolean getIncludesDefaultLanguage() {
			return includesDefaultLanguage;
		}
		
		@Override
		@RosettaAttribute("customElection")
		public String getCustomElection() {
			return customElection;
		}
		
		@Override
		public ReturnAmount build() {
			return this;
		}
		
		@Override
		public ReturnAmount.ReturnAmountBuilder toBuilder() {
			ReturnAmount.ReturnAmountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReturnAmount.ReturnAmountBuilder builder) {
			ofNullable(getIncludesDefaultLanguage()).ifPresent(builder::setIncludesDefaultLanguage);
			ofNullable(getCustomElection()).ifPresent(builder::setCustomElection);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnAmount _that = getType().cast(o);
		
			if (!Objects.equals(includesDefaultLanguage, _that.getIncludesDefaultLanguage())) return false;
			if (!Objects.equals(customElection, _that.getCustomElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (includesDefaultLanguage != null ? includesDefaultLanguage.hashCode() : 0);
			_result = 31 * _result + (customElection != null ? customElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnAmount {" +
				"includesDefaultLanguage=" + this.includesDefaultLanguage + ", " +
				"customElection=" + this.customElection +
			'}';
		}
	}

	/*********************** Builder Implementation of ReturnAmount  ***********************/
	class ReturnAmountBuilderImpl implements ReturnAmount.ReturnAmountBuilder {
	
		protected Boolean includesDefaultLanguage;
		protected String customElection;
	
		public ReturnAmountBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("includesDefaultLanguage")
		public Boolean getIncludesDefaultLanguage() {
			return includesDefaultLanguage;
		}
		
		@Override
		@RosettaAttribute("customElection")
		public String getCustomElection() {
			return customElection;
		}
		
	
		@Override
		@RosettaAttribute("includesDefaultLanguage")
		public ReturnAmount.ReturnAmountBuilder setIncludesDefaultLanguage(Boolean includesDefaultLanguage) {
			this.includesDefaultLanguage = includesDefaultLanguage==null?null:includesDefaultLanguage;
			return this;
		}
		@Override
		@RosettaAttribute("customElection")
		public ReturnAmount.ReturnAmountBuilder setCustomElection(String customElection) {
			this.customElection = customElection==null?null:customElection;
			return this;
		}
		
		@Override
		public ReturnAmount build() {
			return new ReturnAmount.ReturnAmountImpl(this);
		}
		
		@Override
		public ReturnAmount.ReturnAmountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnAmount.ReturnAmountBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIncludesDefaultLanguage()!=null) return true;
			if (getCustomElection()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnAmount.ReturnAmountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReturnAmount.ReturnAmountBuilder o = (ReturnAmount.ReturnAmountBuilder) other;
			
			
			merger.mergeBasic(getIncludesDefaultLanguage(), o.getIncludesDefaultLanguage(), this::setIncludesDefaultLanguage);
			merger.mergeBasic(getCustomElection(), o.getCustomElection(), this::setCustomElection);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnAmount _that = getType().cast(o);
		
			if (!Objects.equals(includesDefaultLanguage, _that.getIncludesDefaultLanguage())) return false;
			if (!Objects.equals(customElection, _that.getCustomElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (includesDefaultLanguage != null ? includesDefaultLanguage.hashCode() : 0);
			_result = 31 * _result + (customElection != null ? customElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnAmountBuilder {" +
				"includesDefaultLanguage=" + this.includesDefaultLanguage + ", " +
				"customElection=" + this.customElection +
			'}';
		}
	}
}
