package cdm.product.template;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.template.CalendarSpread;
import cdm.product.template.CalendarSpread.CalendarSpreadBuilder;
import cdm.product.template.CalendarSpread.CalendarSpreadBuilderImpl;
import cdm.product.template.CalendarSpread.CalendarSpreadImpl;
import cdm.product.template.meta.CalendarSpreadMeta;
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
 * A type for defining a calendar spread feature.
 * @version ${project.version}
 */
@RosettaDataType(value="CalendarSpread", builder=CalendarSpread.CalendarSpreadBuilderImpl.class, version="${project.version}")
public interface CalendarSpread extends RosettaModelObject {

	CalendarSpreadMeta metaData = new CalendarSpreadMeta();

	/*********************** Getter Methods  ***********************/
	AdjustableOrRelativeDate getExpirationDateTwo();

	/*********************** Build Methods  ***********************/
	CalendarSpread build();
	
	CalendarSpread.CalendarSpreadBuilder toBuilder();
	
	static CalendarSpread.CalendarSpreadBuilder builder() {
		return new CalendarSpread.CalendarSpreadBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalendarSpread> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalendarSpread> getType() {
		return CalendarSpread.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("expirationDateTwo"), processor, AdjustableOrRelativeDate.class, getExpirationDateTwo());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalendarSpreadBuilder extends CalendarSpread, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDateTwo();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getExpirationDateTwo();
		CalendarSpread.CalendarSpreadBuilder setExpirationDateTwo(AdjustableOrRelativeDate expirationDateTwo);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("expirationDateTwo"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getExpirationDateTwo());
		}
		

		CalendarSpread.CalendarSpreadBuilder prune();
	}

	/*********************** Immutable Implementation of CalendarSpread  ***********************/
	class CalendarSpreadImpl implements CalendarSpread {
		private final AdjustableOrRelativeDate expirationDateTwo;
		
		protected CalendarSpreadImpl(CalendarSpread.CalendarSpreadBuilder builder) {
			this.expirationDateTwo = ofNullable(builder.getExpirationDateTwo()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("expirationDateTwo")
		public AdjustableOrRelativeDate getExpirationDateTwo() {
			return expirationDateTwo;
		}
		
		@Override
		public CalendarSpread build() {
			return this;
		}
		
		@Override
		public CalendarSpread.CalendarSpreadBuilder toBuilder() {
			CalendarSpread.CalendarSpreadBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalendarSpread.CalendarSpreadBuilder builder) {
			ofNullable(getExpirationDateTwo()).ifPresent(builder::setExpirationDateTwo);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalendarSpread _that = getType().cast(o);
		
			if (!Objects.equals(expirationDateTwo, _that.getExpirationDateTwo())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (expirationDateTwo != null ? expirationDateTwo.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalendarSpread {" +
				"expirationDateTwo=" + this.expirationDateTwo +
			'}';
		}
	}

	/*********************** Builder Implementation of CalendarSpread  ***********************/
	class CalendarSpreadBuilderImpl implements CalendarSpread.CalendarSpreadBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder expirationDateTwo;
	
		public CalendarSpreadBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("expirationDateTwo")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getExpirationDateTwo() {
			return expirationDateTwo;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDateTwo() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (expirationDateTwo!=null) {
				result = expirationDateTwo;
			}
			else {
				result = expirationDateTwo = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("expirationDateTwo")
		public CalendarSpread.CalendarSpreadBuilder setExpirationDateTwo(AdjustableOrRelativeDate expirationDateTwo) {
			this.expirationDateTwo = expirationDateTwo==null?null:expirationDateTwo.toBuilder();
			return this;
		}
		
		@Override
		public CalendarSpread build() {
			return new CalendarSpread.CalendarSpreadImpl(this);
		}
		
		@Override
		public CalendarSpread.CalendarSpreadBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalendarSpread.CalendarSpreadBuilder prune() {
			if (expirationDateTwo!=null && !expirationDateTwo.prune().hasData()) expirationDateTwo = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExpirationDateTwo()!=null && getExpirationDateTwo().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalendarSpread.CalendarSpreadBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalendarSpread.CalendarSpreadBuilder o = (CalendarSpread.CalendarSpreadBuilder) other;
			
			merger.mergeRosetta(getExpirationDateTwo(), o.getExpirationDateTwo(), this::setExpirationDateTwo);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalendarSpread _that = getType().cast(o);
		
			if (!Objects.equals(expirationDateTwo, _that.getExpirationDateTwo())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (expirationDateTwo != null ? expirationDateTwo.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalendarSpreadBuilder {" +
				"expirationDateTwo=" + this.expirationDateTwo +
			'}';
		}
	}
}
