package cdm.observable.asset;

import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.DividendApplicability.DividendApplicabilityBuilder;
import cdm.observable.asset.DividendApplicability.DividendApplicabilityBuilderImpl;
import cdm.observable.asset.DividendApplicability.DividendApplicabilityImpl;
import cdm.observable.asset.meta.DividendApplicabilityMeta;
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
 * The parameters which define whether dividends are applicable
 * @version ${project.version}
 */
@RosettaDataType(value="DividendApplicability", builder=DividendApplicability.DividendApplicabilityBuilderImpl.class, version="${project.version}")
public interface DividendApplicability extends RosettaModelObject {

	DividendApplicabilityMeta metaData = new DividendApplicabilityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * If present and true, then options exchange dividends are applicable.
	 */
	Boolean getOptionsExchangeDividends();
	/**
	 * If present and true, then additional dividends are applicable.
	 */
	Boolean getAdditionalDividends();
	/**
	 * Represents the European Master Confirmation value of &#39;All Dividends&#39; which, when applicable, signifies that, for a given Ex-Date, the daily observed Share Price for that day is adjusted (reduced) by the cash dividend and/or the cash value of any non cash dividend per Share (including Extraordinary Dividends) declared by the Issuer. All Dividends in accordance with the ISDA 2002 Equity Derivatives Definitions.
	 */
	Boolean getAllDividends();

	/*********************** Build Methods  ***********************/
	DividendApplicability build();
	
	DividendApplicability.DividendApplicabilityBuilder toBuilder();
	
	static DividendApplicability.DividendApplicabilityBuilder builder() {
		return new DividendApplicability.DividendApplicabilityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendApplicability> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendApplicability> getType() {
		return DividendApplicability.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("optionsExchangeDividends"), Boolean.class, getOptionsExchangeDividends(), this);
		processor.processBasic(path.newSubPath("additionalDividends"), Boolean.class, getAdditionalDividends(), this);
		processor.processBasic(path.newSubPath("allDividends"), Boolean.class, getAllDividends(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendApplicabilityBuilder extends DividendApplicability, RosettaModelObjectBuilder {
		DividendApplicability.DividendApplicabilityBuilder setOptionsExchangeDividends(Boolean optionsExchangeDividends);
		DividendApplicability.DividendApplicabilityBuilder setAdditionalDividends(Boolean additionalDividends);
		DividendApplicability.DividendApplicabilityBuilder setAllDividends(Boolean allDividends);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("optionsExchangeDividends"), Boolean.class, getOptionsExchangeDividends(), this);
			processor.processBasic(path.newSubPath("additionalDividends"), Boolean.class, getAdditionalDividends(), this);
			processor.processBasic(path.newSubPath("allDividends"), Boolean.class, getAllDividends(), this);
		}
		

		DividendApplicability.DividendApplicabilityBuilder prune();
	}

	/*********************** Immutable Implementation of DividendApplicability  ***********************/
	class DividendApplicabilityImpl implements DividendApplicability {
		private final Boolean optionsExchangeDividends;
		private final Boolean additionalDividends;
		private final Boolean allDividends;
		
		protected DividendApplicabilityImpl(DividendApplicability.DividendApplicabilityBuilder builder) {
			this.optionsExchangeDividends = builder.getOptionsExchangeDividends();
			this.additionalDividends = builder.getAdditionalDividends();
			this.allDividends = builder.getAllDividends();
		}
		
		@Override
		@RosettaAttribute("optionsExchangeDividends")
		public Boolean getOptionsExchangeDividends() {
			return optionsExchangeDividends;
		}
		
		@Override
		@RosettaAttribute("additionalDividends")
		public Boolean getAdditionalDividends() {
			return additionalDividends;
		}
		
		@Override
		@RosettaAttribute("allDividends")
		public Boolean getAllDividends() {
			return allDividends;
		}
		
		@Override
		public DividendApplicability build() {
			return this;
		}
		
		@Override
		public DividendApplicability.DividendApplicabilityBuilder toBuilder() {
			DividendApplicability.DividendApplicabilityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendApplicability.DividendApplicabilityBuilder builder) {
			ofNullable(getOptionsExchangeDividends()).ifPresent(builder::setOptionsExchangeDividends);
			ofNullable(getAdditionalDividends()).ifPresent(builder::setAdditionalDividends);
			ofNullable(getAllDividends()).ifPresent(builder::setAllDividends);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendApplicability _that = getType().cast(o);
		
			if (!Objects.equals(optionsExchangeDividends, _that.getOptionsExchangeDividends())) return false;
			if (!Objects.equals(additionalDividends, _that.getAdditionalDividends())) return false;
			if (!Objects.equals(allDividends, _that.getAllDividends())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (optionsExchangeDividends != null ? optionsExchangeDividends.hashCode() : 0);
			_result = 31 * _result + (additionalDividends != null ? additionalDividends.hashCode() : 0);
			_result = 31 * _result + (allDividends != null ? allDividends.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendApplicability {" +
				"optionsExchangeDividends=" + this.optionsExchangeDividends + ", " +
				"additionalDividends=" + this.additionalDividends + ", " +
				"allDividends=" + this.allDividends +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendApplicability  ***********************/
	class DividendApplicabilityBuilderImpl implements DividendApplicability.DividendApplicabilityBuilder {
	
		protected Boolean optionsExchangeDividends;
		protected Boolean additionalDividends;
		protected Boolean allDividends;
	
		public DividendApplicabilityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("optionsExchangeDividends")
		public Boolean getOptionsExchangeDividends() {
			return optionsExchangeDividends;
		}
		
		@Override
		@RosettaAttribute("additionalDividends")
		public Boolean getAdditionalDividends() {
			return additionalDividends;
		}
		
		@Override
		@RosettaAttribute("allDividends")
		public Boolean getAllDividends() {
			return allDividends;
		}
		
	
		@Override
		@RosettaAttribute("optionsExchangeDividends")
		public DividendApplicability.DividendApplicabilityBuilder setOptionsExchangeDividends(Boolean optionsExchangeDividends) {
			this.optionsExchangeDividends = optionsExchangeDividends==null?null:optionsExchangeDividends;
			return this;
		}
		@Override
		@RosettaAttribute("additionalDividends")
		public DividendApplicability.DividendApplicabilityBuilder setAdditionalDividends(Boolean additionalDividends) {
			this.additionalDividends = additionalDividends==null?null:additionalDividends;
			return this;
		}
		@Override
		@RosettaAttribute("allDividends")
		public DividendApplicability.DividendApplicabilityBuilder setAllDividends(Boolean allDividends) {
			this.allDividends = allDividends==null?null:allDividends;
			return this;
		}
		
		@Override
		public DividendApplicability build() {
			return new DividendApplicability.DividendApplicabilityImpl(this);
		}
		
		@Override
		public DividendApplicability.DividendApplicabilityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendApplicability.DividendApplicabilityBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOptionsExchangeDividends()!=null) return true;
			if (getAdditionalDividends()!=null) return true;
			if (getAllDividends()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendApplicability.DividendApplicabilityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendApplicability.DividendApplicabilityBuilder o = (DividendApplicability.DividendApplicabilityBuilder) other;
			
			
			merger.mergeBasic(getOptionsExchangeDividends(), o.getOptionsExchangeDividends(), this::setOptionsExchangeDividends);
			merger.mergeBasic(getAdditionalDividends(), o.getAdditionalDividends(), this::setAdditionalDividends);
			merger.mergeBasic(getAllDividends(), o.getAllDividends(), this::setAllDividends);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendApplicability _that = getType().cast(o);
		
			if (!Objects.equals(optionsExchangeDividends, _that.getOptionsExchangeDividends())) return false;
			if (!Objects.equals(additionalDividends, _that.getAdditionalDividends())) return false;
			if (!Objects.equals(allDividends, _that.getAllDividends())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (optionsExchangeDividends != null ? optionsExchangeDividends.hashCode() : 0);
			_result = 31 * _result + (additionalDividends != null ? additionalDividends.hashCode() : 0);
			_result = 31 * _result + (allDividends != null ? allDividends.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendApplicabilityBuilder {" +
				"optionsExchangeDividends=" + this.optionsExchangeDividends + ", " +
				"additionalDividends=" + this.additionalDividends + ", " +
				"allDividends=" + this.allDividends +
			'}';
		}
	}
}
