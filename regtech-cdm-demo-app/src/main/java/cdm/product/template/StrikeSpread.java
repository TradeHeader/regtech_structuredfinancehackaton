package cdm.product.template;

import cdm.product.template.OptionStrike;
import cdm.product.template.StrikeSpread;
import cdm.product.template.StrikeSpread.StrikeSpreadBuilder;
import cdm.product.template.StrikeSpread.StrikeSpreadBuilderImpl;
import cdm.product.template.StrikeSpread.StrikeSpreadImpl;
import cdm.product.template.meta.StrikeSpreadMeta;
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
 * A class for defining a strike spread feature.
 * @version ${project.version}
 */
@RosettaDataType(value="StrikeSpread", builder=StrikeSpread.StrikeSpreadBuilderImpl.class, version="${project.version}")
public interface StrikeSpread extends RosettaModelObject {

	StrikeSpreadMeta metaData = new StrikeSpreadMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Upper strike in a strike spread.
	 */
	OptionStrike getUpperStrike();
	/**
	 * Number of options at the upper strike price in a strike spread.
	 */
	BigDecimal getUpperStrikeNumberOfOptions();

	/*********************** Build Methods  ***********************/
	StrikeSpread build();
	
	StrikeSpread.StrikeSpreadBuilder toBuilder();
	
	static StrikeSpread.StrikeSpreadBuilder builder() {
		return new StrikeSpread.StrikeSpreadBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StrikeSpread> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StrikeSpread> getType() {
		return StrikeSpread.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("upperStrike"), processor, OptionStrike.class, getUpperStrike());
		processor.processBasic(path.newSubPath("upperStrikeNumberOfOptions"), BigDecimal.class, getUpperStrikeNumberOfOptions(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface StrikeSpreadBuilder extends StrikeSpread, RosettaModelObjectBuilder {
		OptionStrike.OptionStrikeBuilder getOrCreateUpperStrike();
		OptionStrike.OptionStrikeBuilder getUpperStrike();
		StrikeSpread.StrikeSpreadBuilder setUpperStrike(OptionStrike upperStrike);
		StrikeSpread.StrikeSpreadBuilder setUpperStrikeNumberOfOptions(BigDecimal upperStrikeNumberOfOptions);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("upperStrike"), processor, OptionStrike.OptionStrikeBuilder.class, getUpperStrike());
			processor.processBasic(path.newSubPath("upperStrikeNumberOfOptions"), BigDecimal.class, getUpperStrikeNumberOfOptions(), this);
		}
		

		StrikeSpread.StrikeSpreadBuilder prune();
	}

	/*********************** Immutable Implementation of StrikeSpread  ***********************/
	class StrikeSpreadImpl implements StrikeSpread {
		private final OptionStrike upperStrike;
		private final BigDecimal upperStrikeNumberOfOptions;
		
		protected StrikeSpreadImpl(StrikeSpread.StrikeSpreadBuilder builder) {
			this.upperStrike = ofNullable(builder.getUpperStrike()).map(f->f.build()).orElse(null);
			this.upperStrikeNumberOfOptions = builder.getUpperStrikeNumberOfOptions();
		}
		
		@Override
		@RosettaAttribute("upperStrike")
		public OptionStrike getUpperStrike() {
			return upperStrike;
		}
		
		@Override
		@RosettaAttribute("upperStrikeNumberOfOptions")
		public BigDecimal getUpperStrikeNumberOfOptions() {
			return upperStrikeNumberOfOptions;
		}
		
		@Override
		public StrikeSpread build() {
			return this;
		}
		
		@Override
		public StrikeSpread.StrikeSpreadBuilder toBuilder() {
			StrikeSpread.StrikeSpreadBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StrikeSpread.StrikeSpreadBuilder builder) {
			ofNullable(getUpperStrike()).ifPresent(builder::setUpperStrike);
			ofNullable(getUpperStrikeNumberOfOptions()).ifPresent(builder::setUpperStrikeNumberOfOptions);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StrikeSpread _that = getType().cast(o);
		
			if (!Objects.equals(upperStrike, _that.getUpperStrike())) return false;
			if (!Objects.equals(upperStrikeNumberOfOptions, _that.getUpperStrikeNumberOfOptions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (upperStrike != null ? upperStrike.hashCode() : 0);
			_result = 31 * _result + (upperStrikeNumberOfOptions != null ? upperStrikeNumberOfOptions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrikeSpread {" +
				"upperStrike=" + this.upperStrike + ", " +
				"upperStrikeNumberOfOptions=" + this.upperStrikeNumberOfOptions +
			'}';
		}
	}

	/*********************** Builder Implementation of StrikeSpread  ***********************/
	class StrikeSpreadBuilderImpl implements StrikeSpread.StrikeSpreadBuilder {
	
		protected OptionStrike.OptionStrikeBuilder upperStrike;
		protected BigDecimal upperStrikeNumberOfOptions;
	
		public StrikeSpreadBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("upperStrike")
		public OptionStrike.OptionStrikeBuilder getUpperStrike() {
			return upperStrike;
		}
		
		@Override
		public OptionStrike.OptionStrikeBuilder getOrCreateUpperStrike() {
			OptionStrike.OptionStrikeBuilder result;
			if (upperStrike!=null) {
				result = upperStrike;
			}
			else {
				result = upperStrike = OptionStrike.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("upperStrikeNumberOfOptions")
		public BigDecimal getUpperStrikeNumberOfOptions() {
			return upperStrikeNumberOfOptions;
		}
		
	
		@Override
		@RosettaAttribute("upperStrike")
		public StrikeSpread.StrikeSpreadBuilder setUpperStrike(OptionStrike upperStrike) {
			this.upperStrike = upperStrike==null?null:upperStrike.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("upperStrikeNumberOfOptions")
		public StrikeSpread.StrikeSpreadBuilder setUpperStrikeNumberOfOptions(BigDecimal upperStrikeNumberOfOptions) {
			this.upperStrikeNumberOfOptions = upperStrikeNumberOfOptions==null?null:upperStrikeNumberOfOptions;
			return this;
		}
		
		@Override
		public StrikeSpread build() {
			return new StrikeSpread.StrikeSpreadImpl(this);
		}
		
		@Override
		public StrikeSpread.StrikeSpreadBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrikeSpread.StrikeSpreadBuilder prune() {
			if (upperStrike!=null && !upperStrike.prune().hasData()) upperStrike = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUpperStrike()!=null && getUpperStrike().hasData()) return true;
			if (getUpperStrikeNumberOfOptions()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrikeSpread.StrikeSpreadBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			StrikeSpread.StrikeSpreadBuilder o = (StrikeSpread.StrikeSpreadBuilder) other;
			
			merger.mergeRosetta(getUpperStrike(), o.getUpperStrike(), this::setUpperStrike);
			
			merger.mergeBasic(getUpperStrikeNumberOfOptions(), o.getUpperStrikeNumberOfOptions(), this::setUpperStrikeNumberOfOptions);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StrikeSpread _that = getType().cast(o);
		
			if (!Objects.equals(upperStrike, _that.getUpperStrike())) return false;
			if (!Objects.equals(upperStrikeNumberOfOptions, _that.getUpperStrikeNumberOfOptions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (upperStrike != null ? upperStrike.hashCode() : 0);
			_result = 31 * _result + (upperStrikeNumberOfOptions != null ? upperStrikeNumberOfOptions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrikeSpreadBuilder {" +
				"upperStrike=" + this.upperStrike + ", " +
				"upperStrikeNumberOfOptions=" + this.upperStrikeNumberOfOptions +
			'}';
		}
	}
}
